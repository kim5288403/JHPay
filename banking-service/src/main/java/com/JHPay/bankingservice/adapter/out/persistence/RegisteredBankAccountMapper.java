package com.JHPay.bankingservice.adapter.out.persistence;

import com.JHPay.bankingservice.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJapEntity registeredBankAccountJapEntity) {
        return RegisteredBankAccount.generateRegisteredBankAccount(
                new RegisteredBankAccount.RegisteredBankAccountId(registeredBankAccountJapEntity.getRegisteredBankAccountId() + ""),
                new RegisteredBankAccount.MembershipId(registeredBankAccountJapEntity.getMembershipId()),
                new RegisteredBankAccount.BankName(registeredBankAccountJapEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(registeredBankAccountJapEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccountJapEntity.isLinkedStatusIsValid())
        );
    }
}
