package com.JHPay.money.adapter.axon.command;

import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class AxonCreateMemberMoneyCommand extends SelfValidating<AxonCreateMemberMoneyCommand> {

    @NotNull
    private String membershipId;

    public AxonCreateMemberMoneyCommand(@NotNull String targetMembershipId) {
        this.membershipId = targetMembershipId;
        this.validateSelf();
    }

    public AxonCreateMemberMoneyCommand() {

    }
}
