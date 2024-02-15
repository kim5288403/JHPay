package com.JHPay.banking.application.service;

import com.JHPay.banking.adapter.axon.command.UpdateFirmBankingRequestCommand;
import com.JHPay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import com.JHPay.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.JHPay.banking.application.port.in.UpdateFirmBankingCommand;
import com.JHPay.banking.application.port.in.UpdateFirmBankingUseCase;
import com.JHPay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.JHPay.banking.application.port.out.RequestFirmBankingPort;
import com.JHPay.banking.domain.FirmBankingRequest;
import com.JHPay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;


@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateFirmBankingService implements UpdateFirmBankingUseCase {

    private final FirmBankingRequestMapper mapper;
    private final RequestFirmBankingPort requestFirmBankingPort;
    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;
    private final CommandGateway commandGateway;

    @Override
    public void updateFirmBankingByEvent(UpdateFirmBankingCommand command) {
        UpdateFirmBankingRequestCommand updateFirmBankingRequestCommand = UpdateFirmBankingRequestCommand
                .builder()
                .aggregateIdentifier(command.getFirmBankingAggregateIdentifier())
                .status(command.getStatus())
                .build();

        commandGateway.send(updateFirmBankingRequestCommand).whenComplete((result, throwable) -> {
           if (throwable != null) {
               System.out.println("updateFirmBankingByEvent throwable :" + throwable);
           }  else {
               System.out.println("updateFirmBankingByEvent result :" + result);
               FirmBankingRequestJpaEntity entity = requestFirmBankingPort.getFirmBankingRequest(
                       new FirmBankingRequest.AggregateIdentifier(command.getFirmBankingAggregateIdentifier())
               );

               // 상태의 변경으로 인한 외부 은행과의 커뮤니케이션
               // if 롤백 -> 0, status 변경도 해주겠지만
               // + 기존 펌뱅킹 정보에서 from <-> to 가 변경된 펌뱅킹을 요청하는 새로운 요청

               entity.setFirmBankingStatus(command.getStatus());
               requestFirmBankingPort.modifyFirmBankingRequest(entity);
           }
        });
    }
}
