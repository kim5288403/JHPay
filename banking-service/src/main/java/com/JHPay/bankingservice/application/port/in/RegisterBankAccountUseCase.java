package com.JHPay.bankingservice.application.port.in;

import com.JHPay.bankingservice.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {

    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
