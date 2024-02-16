package com.JHPay.banking.application.port.out;

import com.JHPay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import com.JHPay.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingPort {

    FirmBankingRequestJpaEntity createFirmBankingRequest(
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAmount moneyAmount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus,
            FirmBankingRequest.AggregateIdentifier aggregateIdentifier
    );

    FirmBankingRequestJpaEntity modifyFirmBankingRequest(
            FirmBankingRequestJpaEntity entity
    );

    FirmBankingRequestJpaEntity getFirmBankingRequest(
            FirmBankingRequest.AggregateIdentifier aggregateIdentifier
    );


}
