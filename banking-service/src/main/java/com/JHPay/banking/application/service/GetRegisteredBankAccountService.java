package com.JHPay.banking.application.service;

import com.JHPay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.JHPay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.JHPay.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.JHPay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.JHPay.banking.domain.RegisteredBankAccount;
import com.JHPay.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class GetRegisteredBankAccountService implements GetRegisteredBankAccountUseCase {
    private final RegisteredBankAccountMapper mapper;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return mapper.mapToDomainEntity(getRegisteredBankAccountPort.getRegisteredBankAccount(command));
    }
}
