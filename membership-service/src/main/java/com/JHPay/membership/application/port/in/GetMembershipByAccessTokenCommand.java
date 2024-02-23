package com.JHPay.membership.application.port.in;


import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public
class GetMembershipByAccessTokenCommand extends SelfValidating<GetMembershipByAccessTokenCommand> {

    private final String accessToken;

    public GetMembershipByAccessTokenCommand(String accessToken) {
        this.accessToken = accessToken;
        this.validateSelf();
    }
}