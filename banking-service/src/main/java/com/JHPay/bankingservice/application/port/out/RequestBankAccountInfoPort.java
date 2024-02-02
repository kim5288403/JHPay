package com.JHPay.bankingservice.application.port.out;

import com.JHPay.bankingservice.adapter.out.external.bank.BankAccount;
import com.JHPay.bankingservice.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
