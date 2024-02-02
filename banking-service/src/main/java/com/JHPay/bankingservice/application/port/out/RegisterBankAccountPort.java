package com.JHPay.bankingservice.application.port.out;

import com.JHPay.bankingservice.adapter.out.persistence.RegisteredBankAccountJapEntity;
import com.JHPay.bankingservice.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountJapEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
