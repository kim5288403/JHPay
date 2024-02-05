package com.JHPay.banking.application.port.out;

import com.JHPay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.JHPay.banking.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {

    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest externalFirmBankingRequest);

}
