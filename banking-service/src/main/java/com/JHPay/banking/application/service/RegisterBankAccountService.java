package com.JHPay.banking.application.service;

import com.JHPay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.JHPay.banking.adapter.out.external.bank.BankAccount;
import com.JHPay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.JHPay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.JHPay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.JHPay.banking.application.port.in.RegisterBankAccountCommand;
import com.JHPay.banking.application.port.in.RegisterBankAccountUseCase;
import com.JHPay.banking.application.port.out.GetMembershipPort;
import com.JHPay.banking.application.port.out.MembershipStatus;
import com.JHPay.banking.application.port.out.RegisterBankAccountPort;
import com.JHPay.banking.application.port.out.RequestBankAccountInfoPort;
import com.JHPay.banking.domain.RegisteredBankAccount;
import com.JHPay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;


@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetMembershipPort getMembershipPort;
    private final CommandGateway commandGateway;


    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        // http call membership 정상 확인
        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }


        // 실제 외부의 은행계좌 정보를 가져오기
        BankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid = bankAccountInfo.isValid();

        if (accountIsValid) {
            RegisteredBankAccountJpaEntity entity = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isValid()),
                    new RegisteredBankAccount.AggregateIdentifier("")
            );

            return mapper.mapToDomainEntity(entity);
        }

        return null;
    }

    @Override
    public void registerBankAccountByEvent(RegisterBankAccountCommand command) {
        commandGateway.send(new CreateRegisteredBankAccountCommand(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber()
                ))
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        System.out.println("registerBankAccountByEvent throwable : " + throwable);
                    } else {
                        // 정상적으로 이벤트 소싱
                        // DB 인설트
                        System.out.println("registerBankAccountByEvent result : " +  result.toString());
                        registerBankAccountPort.createRegisteredBankAccount(
                                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                                new RegisteredBankAccount.BankName(command.getBankName()),
                                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                                new RegisteredBankAccount.LinkedStatusIsValid(command.isValid()),
                                new RegisteredBankAccount.AggregateIdentifier(result.toString())
                        );
                    }
        });
    }
}
