package com.JHPay.money.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.JHPay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.JHPay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.JHPay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.JHPay.money.application.port.out.IncreaseMoneyPort;
import com.JHPay.money.domain.MemberMoney;
import com.JHPay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;


@UseCase
@Transactional
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;

    private final MoneyChangingRequestMapper mapper;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {

        // 머니의 충전, 증액이라는 과정
        // 1. 고객 정보 정상 유효성 검사 (맴버)

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
}
