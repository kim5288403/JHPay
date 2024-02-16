package com.JHPay.money.adapter.axon.aggregate;

import com.JHPay.money.adapter.axon.command.AxonCreateMemberMoneyCommand;
import com.JHPay.money.adapter.axon.command.AxonIncreaseMemberMoneyCommand;
import com.JHPay.money.adapter.axon.command.RechargingMoneyRequestCreateCommand;
import com.JHPay.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.JHPay.money.adapter.axon.event.MemberMoneyCreatedEvent;
import com.JHPay.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.JHPay.money.application.port.out.GetRegisteredBankAccountPort;
import com.JHPay.money.application.port.out.RegisteredBankAccountAggregateIdentifier;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
@Table(name = "member_money")
@Aggregate()
@Data
public class MemberMoneyAggregate {

    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(AxonCreateMemberMoneyCommand command) {
        System.out.println("member money create Axon CommandHandler");
        apply(new MemberMoneyCreatedEvent(command.getMembershipId()));
    }

    @CommandHandler
    public String handle(@NotNull AxonIncreaseMemberMoneyCommand command) {
        System.out.println("member money increase Axon CommandHandler");
        id = command.getAggregateIdentifier();
        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getAmount()));
        return id;
    }
    @CommandHandler
    public void handle(RechargingMoneyRequestCreateCommand command, GetRegisteredBankAccountPort getRegisteredBankAccountPort) {
        System.out.println("RechargingMoneyRequestCreateCommand Handler");
        id = command.getAggregateIdentifier();

        System.out.println("RechargingMoneyRequestCreateCommand Handler command aggregate identifier : " + command.getAggregateIdentifier());
        RegisteredBankAccountAggregateIdentifier bankAccountAggregate = getRegisteredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());
        System.out.println("RechargingMoneyRequestCreateCommand Handler command bankAccountAggregate : " + bankAccountAggregate);
        apply(new RechargingRequestCreatedEvent(
                command.getRechargingRequestId()
                , command.getMembershipId()
                , command.getAmount()
                , bankAccountAggregate.getAggregateIdentifier()
                , bankAccountAggregate.getBankName()
                , bankAccountAggregate.getBankAccountNumber()
        ));
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        System.out.println("member money Axon EventSourcingHandler");
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMemberMoneyEvent event) {
        System.out.println("member money Axon EventSourcingHandler");
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

    public MemberMoneyAggregate() {
    }

    @Override
    public String toString() {
        return "MemberMoneyAggregate{" +
                "id='" + id + '\'' +
                ", membershipId=" + membershipId +
                ", balance=" + balance +
                '}';
    }
}
