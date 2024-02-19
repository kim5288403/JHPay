package com.JHPay.money.application.port.in;

import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMemberMoneyListByMembershipIdsCommand extends SelfValidating<FindMemberMoneyListByMembershipIdsCommand> {

    @NotNull
    private final List<String> membershipId;

    public FindMemberMoneyListByMembershipIdsCommand(@NotNull List<String> membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
