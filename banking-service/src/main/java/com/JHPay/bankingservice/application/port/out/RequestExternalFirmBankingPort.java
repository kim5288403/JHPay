package com.JHPay.bankingservice.application.port.out;

import com.JHPay.bankingservice.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.JHPay.bankingservice.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {

    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest externalFirmBankingRequest);

}
