package com.JHPay.membership.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.membership.application.port.in.RegisterMembershipCommand;
import com.JHPay.membership.application.port.in.RegisterMembershipUseCase;
import com.JHPay.membership.domain.Membership;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    @Operation(summary = "회원 가입 요청", description = "회원 가입")
    Membership registerMembership(@RequestBody RegisterMembershipRequest request) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }
}
