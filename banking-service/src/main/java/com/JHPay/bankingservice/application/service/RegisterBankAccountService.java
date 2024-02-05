package com.JHPay.bankingservice.application.service;

import com.JHPay.bankingservice.adapter.out.external.bank.BankAccount;
import com.JHPay.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.JHPay.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.JHPay.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.JHPay.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.JHPay.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.JHPay.bankingservice.application.port.out.RegisterBankAccountPort;
import com.JHPay.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.JHPay.bankingservice.domain.RegisteredBankAccount;
import com.JHPay.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;


@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;


    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {



        // 실제 외부의 은행계좌 정보를 가져오기
        BankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid = bankAccountInfo.isValid();

        if (accountIsValid) {
            RegisteredBankAccountJpaEntity entity = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isValid())
            );

            return mapper.mapToDomainEntity(entity);
        }

        return null;
    }
}
