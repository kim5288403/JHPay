package com.JHPay.banking.application.port.out;

import com.JHPay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.JHPay.banking.application.port.in.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
