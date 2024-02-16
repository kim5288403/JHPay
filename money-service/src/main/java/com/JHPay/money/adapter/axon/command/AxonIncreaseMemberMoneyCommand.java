package com.JHPay.money.adapter.axon.command;

import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class AxonIncreaseMemberMoneyCommand extends SelfValidating<AxonIncreaseMemberMoneyCommand> {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    @NotNull
    private String membershipId;

    @NotNull
    private int amount;
}
