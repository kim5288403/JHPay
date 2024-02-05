package com.JHPay.banking.application.service;

import com.JHPay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.JHPay.banking.adapter.out.external.bank.FirmBankingResult;
import com.JHPay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import com.JHPay.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.JHPay.banking.application.port.in.RequestFirmBankingCommand;
import com.JHPay.banking.application.port.in.RequestFirmBankingUseCase;
import com.JHPay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.JHPay.banking.application.port.out.RequestFirmBankingPort;
import com.JHPay.banking.domain.FirmBankingRequest;
import com.JHPay.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;


@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

    private final FirmBankingRequestMapper mapper;
    private final RequestFirmBankingPort requestFirmBankingPort;
    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;


    @Override
    public FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command) {

        // 펌뱅킹 요청 상태로 저장
        FirmBankingRequestJpaEntity entity = requestFirmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAccount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(0)
        );

        // 외부 은행에 펌뱅킹 요청
        FirmBankingResult result = requestExternalFirmBankingPort.requestExternalFirmBanking(
                new ExternalFirmBankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccountNumber(),
                        command.getToBankName(),
                        command.getToBankAccountNumber()
                )
        );

        UUID uuid = UUID.randomUUID();
        entity.setUuid(uuid.toString());

        // 결과에 따라서 작성했던 FirmBankingRequest 정보를 업데이트
        if (result.getResultCode() == 0) {
            // 성공
            entity.setFirmBankingStatus(1);
        } else {
            // 실패
            entity.setFirmBankingStatus(2);
        }

        return mapper.mapToDomainEntity(requestFirmBankingPort.modifyFirmBankingRequest(entity), uuid);
    }
}
