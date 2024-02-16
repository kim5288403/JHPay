package com.JHPay.banking.application.port.in;

import com.JHPay.banking.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountUseCase {
    RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
