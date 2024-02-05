package com.JHPay.bankingservice.adapter.out.persistence;

import com.JHPay.bankingservice.application.port.out.RequestFirmBankingPort;
import com.JHPay.bankingservice.domain.FirmBankingRequest;
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
            FirmBankingRequest.FirmBankingStatus firmBankingStatus) {

        return repository.save(
                new FirmBankingRequestJpaEntity(
                        fromBankName.getFromBankName(),
                        fromBankAccountNumber.getFromBankAccountNumber(),
                        toBankName.getToBankName(),
                        toBankAccountNumber.getToBankAccountNumber(),
                        moneyAccount.getMoneyAccount(),
                        firmBankingStatus.getFirmBankingStatus(),
                        UUID.randomUUID()
                )
        );
    }

    @Override
    public FirmBankingRequestJpaEntity modifyFirmBankingRequest(FirmBankingRequestJpaEntity entity) {
        return repository.save(entity);
    }
}
