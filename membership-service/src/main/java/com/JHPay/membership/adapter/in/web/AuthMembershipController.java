package com.JHPay.membership.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.membership.application.port.in.AuthMembershipUseCase;
import com.JHPay.membership.application.port.in.GetMembershipByAccessTokenCommand;
import com.JHPay.membership.application.port.in.LoginMembershipCommand;
import com.JHPay.membership.application.port.in.ValidateTokenCommand;
import com.JHPay.membership.domain.JwtToken;
import com.JHPay.membership.domain.Membership;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AuthMembershipController {

    private final AuthMembershipUseCase authMembershipUseCase;

    @PostMapping(path = "/membership/login")
    @Operation(summary = "로그인", description = "로그인")
    JwtToken findMembershipByMemberId(@RequestBody LoginMembershipRequest request) {
        LoginMembershipCommand command = LoginMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .build();

        return authMembershipUseCase.loginMembership(command);
    }

    @PostMapping(path = "/membership/refresh-token")
    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    JwtToken refreshToken(@RequestBody LoginMembershipRequest request) {
        LoginMembershipCommand command = LoginMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .build();

        return authMembershipUseCase.loginMembership(command);
    }

    @PostMapping(path = "/membership/validate-token")
    @Operation(summary = "토큰 유효성 검사", description = "토큰 유효성 검사")
    boolean validateToken(@RequestBody ValidateTokenRequest request) {

        ValidateTokenCommand command = ValidateTokenCommand.builder()
                .jwtToken(request.getJwtToken())
                .build();

        return authMembershipUseCase.validateToken(command);
    }

    @PostMapping(path = "/membership/membership-by-token")
    @Operation(summary = "토큰 유효성 검사", description = "토큰 유효성 검사")
    Membership getMembershipByAccessToken(@RequestBody GetMembershipByAccessTokenRequest request) {

        GetMembershipByAccessTokenCommand command = GetMembershipByAccessTokenCommand.builder()
                .accessToken(request.getAccessToken())
                .build();

        return authMembershipUseCase.getMembershipByAccessToken(command);
    }

}
