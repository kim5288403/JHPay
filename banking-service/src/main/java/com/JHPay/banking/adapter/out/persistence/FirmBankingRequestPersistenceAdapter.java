package com.JHPay.banking.adapter.out.persistence;

import com.JHPay.banking.application.port.out.RequestFirmBankingPort;
import com.JHPay.banking.domain.FirmBankingRequest;
import com.JHPay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort {

    private final SpringDataFirmBankingRequestRepository repository;

    @Override
    public FirmBankingRequestJpaEntity createFirmBankingRequest(
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAccount moneyAccount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus,
            FirmBankingRequest.AggregateIdentifier aggregateIdentifier) {

        return repository.save(
                new FirmBankingRequestJpaEntity(
                        fromBankName.getFromBankName(),
                        fromBankAccountNumber.getFromBankAccountNumber(),
                        toBankName.getToBankName(),
                        toBankAccountNumber.getToBankAccountNumber(),
                        moneyAccount.getMoneyAccount(),
                        firmBankingStatus.getFirmBankingStatus(),
                        UUID.randomUUID(),
                        aggregateIdentifier.getAggregateIdentifier()
                )
        );
    }

    @Override
    public FirmBankingRequestJpaEntity modifyFirmBankingRequest(FirmBankingRequestJpaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public FirmBankingRequestJpaEntity getFirmBankingRequest(FirmBankingRequest.AggregateIdentifier aggregateIdentifier) {
        return repository.findByAggregateIdentifier(aggregateIdentifier.getAggregateIdentifier());
    }
}
