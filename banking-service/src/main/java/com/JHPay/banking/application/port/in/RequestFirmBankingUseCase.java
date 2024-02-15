package com.JHPay.banking.application.port.in;

import com.JHPay.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingUseCase {

    FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command);
    void requestFirmBankingByEvent(RequestFirmBankingCommand command);
}
