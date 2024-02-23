package com.JHPay.membership.application.port.in;

import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ValidateTokenCommand extends SelfValidating<ValidateTokenCommand> {
    private final String jwtToken;

    public ValidateTokenCommand(String jwtToken) {
        this.jwtToken = jwtToken;

        this.validateSelf();
    }
}
