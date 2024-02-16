package com.JHPay.banking.adapter.axon.aggregate;

import com.JHPay.banking.adapter.axon.command.CreateFirmBankingRequestCommand;
import com.JHPay.banking.adapter.axon.command.UpdateFirmBankingRequestCommand;
import com.JHPay.banking.adapter.axon.event.FirmBankingRequestCreatedEvent;
import com.JHPay.banking.adapter.axon.event.FirmBankingRequestUpdatedEvent;
import com.JHPay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.JHPay.banking.adapter.out.external.bank.FirmBankingResult;
import com.JHPay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.JHPay.banking.application.port.out.RequestFirmBankingPort;
import com.JHPay.banking.domain.FirmBankingRequest;
import com.JHPay.common.command.RequestFirmBankingCommand;
import com.JHPay.common.command.RollbackFirmBankingRequestCommand;
import com.JHPay.common.event.RequestFirmBankingFinishedEvent;
import com.JHPay.common.event.RollbackFirmBankingFinishedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class FirmBankingRequestAggregate {

    @AggregateIdentifier
    private String id;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAccount; // only won

    private int firmBankingStatus; // 0: 요청, 1: 완료, 2: 실패

    @CommandHandler
    public FirmBankingRequestAggregate (CreateFirmBankingRequestCommand command) {
        System.out.println("create FirmBankingRequest command handler");

        apply(new FirmBankingRequestCreatedEvent(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAccount()
                ));
    }

    @CommandHandler
    public String handle (UpdateFirmBankingRequestCommand command) {
        System.out.println("Update FirmBankingRequest command handler");
        id = command.getAggregateIdentifier();

        apply(new FirmBankingRequestUpdatedEvent(
                command.getStatus()
        ));

        return id;
    }

    @CommandHandler
    public FirmBankingRequestAggregate(
            @NotNull RollbackFirmBankingRequestCommand command,
            RequestFirmBankingPort firmBankingPort,
            RequestExternalFirmBankingPort externalFirmBankingPort
    ) {
        System.out.println("RollbackFirmBankingRequestCommand Handler");
        id = UUID.randomUUID().toString();

        firmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName("jh-bank"),
                new FirmBankingRequest.FromBankAccountNumber("123-333-9999"),
                new FirmBankingRequest.ToBankName(command.getBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(0),
                new FirmBankingRequest.AggregateIdentifier(id));

        // firmBanking!
        externalFirmBankingPort.requestExternalFirmBanking(
                new ExternalFirmBankingRequest(
                        "jhBank",
                        "123-333-9999",
                        command.getBankName(),
                        command.getBankAccountNumber(),
                        command.getMoneyAmount()
                ));

        apply(new RollbackFirmBankingFinishedEvent(
                command.getRollbackFirmBankingId(),
                command.getMembershipId(),
                id)
        );
    }

    @CommandHandler
    public FirmBankingRequestAggregate(
            RequestFirmBankingCommand command,
            RequestFirmBankingPort firmBankingPort,
            RequestExternalFirmBankingPort externalFirmBankingPort){
        System.out.println("RequestFirmBankingCommand Handler");
        id = command.getAggregateIdentifier();

        firmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getToBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.ToBankName("jhPay-bank"),
                new FirmBankingRequest.ToBankAccountNumber("123-333-9999"),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(0),
                new FirmBankingRequest.AggregateIdentifier(id));

        // firmBanking!
        FirmBankingResult firmbankingResult = externalFirmBankingPort.requestExternalFirmBanking(new ExternalFirmBankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        int resultCode = firmbankingResult.getResultCode();

        // 0. 성공, 1. 실패
        apply(new RequestFirmBankingFinishedEvent(
                command.getRequestFirmBankingId(),
                command.getRechargeRequestId(),
                command.getMembershipId(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                resultCode,
                id
        ));
    }

    @EventSourcingHandler
    public void on (FirmBankingRequestCreatedEvent event) {
        System.out.println("FirmBankingRequestCreatedEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
    }

    @EventSourcingHandler
    public void on (FirmBankingRequestUpdatedEvent event) {
        System.out.println("FirmBankingRequestUpdatedEvent Sourcing Handler");
        firmBankingStatus = event.getStatus();
    }


}
