package com.JHPay.settlement.application.port.out;

public interface RequestFirmBankingPort {

    void requestFirmBanking(String bankName, String bankAccountNumber, int moneyAmount);

}
