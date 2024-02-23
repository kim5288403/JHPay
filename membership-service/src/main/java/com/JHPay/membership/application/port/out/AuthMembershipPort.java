package com.JHPay.membership.application.port.out;

import com.JHPay.membership.domain.Membership;

public interface AuthMembershipPort {

    String generateAccessToken (
            Membership.MembershipId membershipId
    );

    String generateRefreshToken (
            Membership.MembershipId membershipId
    );

    // 토큰 유효성 검사
    boolean validateToken(String token);

    // accessToken 으로 어떤 맴버쉽 ID 를 가지는지 확인
    Membership.MembershipId parseMembershipIdFromToken(String accessToken);
}
