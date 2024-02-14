package com.JHPay.money.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.money.adapter.axon.command.AxonCreateMemberMoneyCommand;
import com.JHPay.money.application.port.in.CreateMemberMoneyCommand;
import com.JHPay.money.application.port.in.CreateMemberMoneyPort;
import com.JHPay.money.application.port.in.CreateMemberMoneyUseCase;
import com.JHPay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CreateMemberMoneyService implements CreateMemberMoneyUseCase {


    private final CommandGateway commandGateway;

    private final CreateMemberMoneyPort createMemberMoneyPort;

    @Override
    public void createMemberMoney(CreateMemberMoneyCommand command) {
        AxonCreateMemberMoneyCommand axonCommand = AxonCreateMemberMoneyCommand
                .builder()
                .membershipId(command.getMembershipId())
                .build();

        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                System.out.println("createMemberMoney throwable : " + throwable);
                throw new RuntimeException(throwable);
            } else {
                System.out.println("createMemberMoney result : " + result);
                createMemberMoneyPort.createMemberMoney(
                        new MemberMoney.MembershipId(command.getMembershipId()),
                        new MemberMoney.MoneyAggregateIdentifier(result.toString())
                );
            }
        });
    }
}
