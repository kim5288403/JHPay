package com.JHPay.membership.application.port.in;


import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public
class RefreshTokenCommand extends SelfValidating<RefreshTokenCommand> {

    private final String refreshToken;

    public RefreshTokenCommand(String refreshToken) {
        this.refreshToken = refreshToken;
        this.validateSelf();
    }
}