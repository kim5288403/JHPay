package com.JHPay.money.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.money.application.port.in.FindMemberMoneyListByMembershipIdsCommand;
import com.JHPay.money.application.port.in.FindMoneyRequestUseCase;
import com.JHPay.money.domain.MemberMoney;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMemberMoneyController {

    private final FindMoneyRequestUseCase findMoneyRequestUseCase;

    @PostMapping(path = "/money/member-money")
    @Operation(summary = "맴버 머니 가져오기", description = "맴버 머니 가져오기")
    List<MemberMoney> findMemberMoneyListByMembershipIdsRequest(@RequestBody FindMemberMoneyListByMembershipIdsRequest request) {
        FindMemberMoneyListByMembershipIdsCommand command = FindMemberMoneyListByMembershipIdsCommand
                .builder()
                .membershipId(request.getTargetMembershipId())
                .build();

        return findMoneyRequestUseCase.findMemberMoneyListByMembershipIds(command);
    }


}
