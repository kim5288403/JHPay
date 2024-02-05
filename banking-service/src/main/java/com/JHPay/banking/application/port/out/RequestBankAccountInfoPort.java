package com.JHPay.banking.application.port.out;

import com.JHPay.banking.adapter.out.external.bank.BankAccount;
import com.JHPay.banking.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
