package com.JHPay.bankingservice.application.port.in;

import com.JHPay.bankingservice.domain.FirmBankingRequest;

public interface RequestFirmBankingUseCase {

    FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command);
}
