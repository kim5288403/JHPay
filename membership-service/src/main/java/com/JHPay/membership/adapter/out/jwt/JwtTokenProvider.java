package com.JHPay.membership.adapter.out.jwt;

import com.JHPay.membership.application.port.out.AuthMembershipPort;
import com.JHPay.membership.domain.Membership;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider implements AuthMembershipPort {

    private String jwtSecret; // 비밀 키

    private long accessExpirationInMs; // accessToken 유효시간

    private long refreshTokenExpirationInMs; // refreshToken 유효시간

    public JwtTokenProvider () {
        jwtSecret = "NYd4nEtyLtcU7cpS/1HTFVmQJd7MmrP+HafWoXZjWNOL7qKccOOUfQNEx5yvG6dfdpuBeyMs9eEbRmdBrPQCNg==";
        accessExpirationInMs = 1000L * 20; // 30초
        refreshTokenExpirationInMs = 1000L * 60; // 1분
    }

    @Override
    public String generateAccessToken(Membership.MembershipId membershipId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessExpirationInMs);

        return Jwts.builder()
                .setSubject(membershipId.getMembershipId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public String generateRefreshToken(Membership.MembershipId membershipId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpirationInMs);

        return Jwts.builder()
                .setSubject(membershipId.getMembershipId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            // Invalid JWT token: 유효하지 않은 JWT 토큰일 때 발생하는 예외
        } catch (ExpiredJwtException ex) {
            // Expired JWT token: 토큰의 유효기간이 만료된 경우 발생하는 예외
        } catch (UnsupportedJwtException ex) {
            // Unsupported JWT token: 지원하지 않는 JWT 토큰일 때 발생하는 예외
        } catch (IllegalArgumentException ex) {
            // JWT claims string is empty: JWT 토큰이 비어있을 때 발생하는 예외
        }
        return false;
    }

    @Override
    public Membership.MembershipId parseMembershipIdFromToken(String accessToken) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(accessToken).getBody();
        return new Membership.MembershipId(claims.getSubject());
    }
}
