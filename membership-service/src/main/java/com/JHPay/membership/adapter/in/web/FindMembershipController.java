package com.JHPay.membership.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.membership.application.port.in.FindMembershipCommand;
import com.JHPay.membership.application.port.in.FindMembershipUseCase;
import com.JHPay.membership.domain.Membership;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    @Operation(summary = "회원 정보", description = "회원 정보 가져오기")
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable String membershipId) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();

        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
    }
}
