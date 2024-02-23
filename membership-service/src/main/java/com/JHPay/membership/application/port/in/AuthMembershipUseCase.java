package com.JHPay.membership.application.port.in;

import com.JHPay.membership.domain.JwtToken;
import com.JHPay.membership.domain.Membership;

public interface AuthMembershipUseCase {
    JwtToken loginMembership (LoginMembershipCommand command);

    JwtToken refreshToken(RefreshTokenCommand command);

    boolean validateToken(ValidateTokenCommand command);

    Membership getMembershipByAccessToken(GetMembershipByAccessTokenCommand command);
}
