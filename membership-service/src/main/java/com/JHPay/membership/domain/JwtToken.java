package com.JHPay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtToken {

    private final String membershipId;

    private final String jwtToken;

    private final String refreshToken;

    public static JwtToken generateJwtToken(
            MembershipId membershipId,
            AccessToken accessToken,
            RefreshToken refreshToken
    ) {
        return new JwtToken(
                membershipId.membershipId,
                accessToken.accessToken,
                refreshToken.refreshToken
        );
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }

        String membershipId;
    }

    @Value
    public static class AccessToken {
        public AccessToken(String value) {
            this.accessToken = value;
        }

        String accessToken;
    }

    @Value
    public static class RefreshToken {
        public RefreshToken(String value) {
            this.refreshToken = value;
        }

        String refreshToken;
    }

}
