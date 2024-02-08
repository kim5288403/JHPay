package com.JHPay.remittance.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.remittance.application.port.in.RequestRemittanceCommand;
import com.JHPay.remittance.application.port.in.RequestRemittanceUseCase;
import com.JHPay.remittance.domain.RemittanceRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestRemittanceController {

    private final RequestRemittanceUseCase requestRemittanceUseCase;
    @PostMapping(path = "/remittance/request")
    @Operation(summary = "송금 요청", description = "송금 요청하기")
    RemittanceRequest requestRemittance(@RequestBody RequestRemittanceRequest request) {
        RequestRemittanceCommand command = RequestRemittanceCommand.builder()
                .fromMembershipId(request.getFromMembershipId())
                .toMembershipId(request.getToMembershipId())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .amount(request.getAmount())
                .remittanceType(request.getRemittanceType())
                .build();

        return requestRemittanceUseCase.requestRemittance(command);
    }
}