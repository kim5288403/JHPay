package com.JHPay.bankingservice.adapter.in.web;

import com.JHPay.bankingservice.application.port.in.RequestFirmBankingCommand;
import com.JHPay.bankingservice.application.port.in.RequestFirmBankingUseCase;
import com.JHPay.bankingservice.domain.FirmBankingRequest;
import com.JHPay.common.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final RequestFirmBankingUseCase requestFirmBankingUseCase;

    @PostMapping(path = "/banking/firmbanking/request")
    @Operation(summary = "펌 뱅킹 요청", description = "펌 뱅킹 요청")
    FirmBankingRequest registerMembership(@RequestBody RequestFirmBankingRequest request) {
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankName())
                .toBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        return requestFirmBankingUseCase.requestFirmBanking(command);
    }
}
