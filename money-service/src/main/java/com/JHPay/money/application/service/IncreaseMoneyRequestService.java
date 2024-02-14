package com.JHPay.money.application.service;

import com.JHPay.common.CountDownLatchManager;
import com.JHPay.common.RechargingMoneyTask;
import com.JHPay.common.SubTask;
import com.JHPay.common.UseCase;
import com.JHPay.money.adapter.axon.command.AxonIncreaseMemberMoneyCommand;
import com.JHPay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.JHPay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.JHPay.money.application.port.in.GetMemberMoneyPort;
import com.JHPay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.JHPay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.JHPay.money.application.port.out.GetMembershipPort;
import com.JHPay.money.application.port.out.IncreaseMoneyPort;
import com.JHPay.money.application.port.out.SendRechargingMoneyTaskPort;
import com.JHPay.money.domain.MemberMoney;
import com.JHPay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@UseCase
@Transactional
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;

    private final MoneyChangingRequestMapper mapper;

    private final GetMembershipPort getMembershipPort;

    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;

    private final CountDownLatchManager countDownLatchManager;

    private final CommandGateway commandGateway;

    private final GetMemberMoneyPort getMemberMoneyPort;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {

        // 머니의 충전, 증액이라는 과정
        // 1. 고객 정보 정상 유효성 검사 (맴버)
//        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getTargetMembershipId());
//        if (!membershipStatus.isValid()) {
//            return null;
//        }

        // 2. 고객의 연동된 계좌 유효성 검사 (뱅킹)

        // 3. 법인 계좌 유효성 검사 (뱅킹)

        // 4. 증액을 위한 기록

        // 5. 펌뱅킹 (고객의 연동된 계좌 -> jhPay 법인 계좌) (뱅킹)

        // 6. 결과를 성공/실패로 MoneyChangingRequest 상태값 변동
        // 6-1. 성공 시에 맴버의 MemberMoney 증액
        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
                , command.getAmount());

        if (memberMoneyJpaEntity == null) {
            return null;
        }

        return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingType(0),
                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                new MoneyChangingRequest.ChangingStatus(1),
                new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
        ));
    }

    @Override
    public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {

        // Subtask
        // 각 서비스에 특정 membershipId 로 Validation 을 하기 위한 task

        // 1. subtask, task
        SubTask validMemberTask = SubTask.builder()
                .subTaskName("validMemberTask : " + "맴버쉽 유효성 검사")
                .membershipId(command.getTargetMembershipId())
                .taskType("membership")
                .status("ready")
                .build();

        // Banking sub task
        // Banking account validation
        SubTask validBankingAccountTask = SubTask.builder()
                .subTaskName("validBankingAccountTask : " + "뱅킹 계좌 유효성 검사")
                .membershipId(command.getTargetMembershipId())
                .taskType("banking")
                .status("ready")
                .build();

        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validBankingAccountTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskID(UUID.randomUUID().toString())
                .taskName("Increase Money Task / 머니 충전 타스크")
                .subTaskList(subTaskList)
                .moneyAmount(command.getAmount())
                .membershipID(command.getTargetMembershipId())
                .toBankName("jhBank")
                .build();

        // 2. kafka cluster produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);
        countDownLatchManager.addCountDownLatch(task.getTaskID());

        // 3. wait
        try {
            countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 3-1. task-consumer
        // 등록된 sub-task, status 모두 ok -> task 결과를 produce

        // 4. task result consume
        // 받은 응답을 다시, countDownLatchManager 를 통해서 결과 데이터를 받아야함.
        String result = countDownLatchManager.getDataForKey(task.getTaskID());

        if (result.equals("success")) {
            MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                    new MemberMoney.MembershipId(command.getTargetMembershipId())
                    , command.getAmount());

            if (memberMoneyJpaEntity != null) {
                return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                                new MoneyChangingRequest.ChangingType(1),
                                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                                new MoneyChangingRequest.ChangingStatus(1),
                                new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                        )
                );
            }
        } else {
            // 4-2. Consume fail, Logic
            return null;
        }

        // 5. consume ok, logic
        return null;
    }

    @Override
    public void increaseMoneyRequestByEvent(IncreaseMoneyRequestCommand command) {
        MemberMoneyJpaEntity memberMoneyJpaEntity = getMemberMoneyPort.getMemberMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
        );

        String aggregateIdentifier = memberMoneyJpaEntity.getAggregateIdentifier();

        AxonIncreaseMemberMoneyCommand axonCommand = AxonIncreaseMemberMoneyCommand
                .builder()
                .membershipId(command.getTargetMembershipId())
                .amount(command.getAmount())
                .aggregateIdentifier(aggregateIdentifier)
                .build();

        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                System.out.println("increaseMoney throwable : " + throwable);
                throw new RuntimeException(throwable);
            } else {
                // Increase money
                increaseMoneyPort.increaseMoney(
                        new MemberMoney.MembershipId(command.getTargetMembershipId())
                        , command.getAmount());

                System.out.println("increaseMoney result : " + result);
            }
        });
    }
}
