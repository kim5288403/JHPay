package com.JHPay.banking.adapter.axon.aggregate;

import com.JHPay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.JHPay.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.JHPay.banking.adapter.out.external.bank.BankAccount;
import com.JHPay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.JHPay.banking.application.port.out.RequestBankAccountInfoPort;
import com.JHPay.common.command.CheckRegisteredBankAccountCommand;
import com.JHPay.common.event.CheckedRegisteredBankAccountEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
public class RegisteredBankAccountAggregate {

    @AggregateIdentifier
    private String id;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;

    public RegisteredBankAccountAggregate() {
        // System.out.println("RegisteredBankAccountAggregate Constructor");
    }
    @CommandHandler
    public RegisteredBankAccountAggregate(CreateRegisteredBankAccountCommand command) {
        System.out.println("CreateRegisteredBankAccountCommand Command Handler");

        apply(new CreateRegisteredBankAccountEvent(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber()
        ));
    }

    @CommandHandler
    public void handle(CheckRegisteredBankAccountCommand command, RequestBankAccountInfoPort requestBankAccountInfoPort) {
        System.out.println("CheckRegisteredBankAccountCommand Handler");

        // command 를 통해, 이 어그리거트(RegisteredBankAccount) 가 정상인지 확인
        id = command.getAggregateIdentifier();

        // Check Registered Bank Account;
        BankAccount bankAccount = requestBankAccountInfoPort.getBankAccountInfo(
                new GetBankAccountRequest(command.getBankName(),
                        command.getBankAccountNumber()
                ));

        boolean isValidAccount = bankAccount.isValid();

        String firmBankingUUID = UUID.randomUUID().toString();

        // CheckedRegisteredBankAccountEvent
        apply(new CheckedRegisteredBankAccountEvent(
                        command.getRechargeRequestId()
                        , command.getCheckRegisteredBankAccountId()
                        , command.getMembershipId()
                        , isValidAccount
                        , command.getAmount()
                        , firmBankingUUID
                        , bankAccount.getBankName()
                        , bankAccount.getBankAccountNumber()
                )
        );
    }

    @EventSourcingHandler
    public void on (CreateRegisteredBankAccountEvent event) {
        System.out.println("CreateRegisteredBankAccountEvent Sourcing Handler");

        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankName = event.getBankName();
        bankAccountNumber = event.getBankAccountNumber();
    }
}
