package com.JHPay.membership.application.port.in;

import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class LoginMembershipCommand extends SelfValidating<LoginMembershipCommand> {
    private final String membershipId;

    public LoginMembershipCommand(String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
