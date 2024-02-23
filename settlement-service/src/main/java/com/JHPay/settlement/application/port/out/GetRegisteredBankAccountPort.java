package com.JHPay.settlement.application.port.out;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId);
}
