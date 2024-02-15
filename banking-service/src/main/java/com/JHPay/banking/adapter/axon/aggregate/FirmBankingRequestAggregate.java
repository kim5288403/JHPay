package com.JHPay.banking.adapter.axon.aggregate;

import com.JHPay.banking.adapter.axon.command.CreateFirmBankingRequestCommand;
import com.JHPay.banking.adapter.axon.command.UpdateFirmBankingRequestCommand;
import com.JHPay.banking.adapter.axon.event.FirmBankingRequestCreatedEvent;
import com.JHPay.banking.adapter.axon.event.FirmBankingRequestUpdatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

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
