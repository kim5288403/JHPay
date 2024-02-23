package com.JHPay.banking.adapter.in.web;

import com.JHPay.banking.application.port.in.RequestFirmBankingCommand;
import com.JHPay.banking.application.port.in.RequestFirmBankingUseCase;
import com.JHPay.banking.application.port.in.UpdateFirmBankingCommand;
import com.JHPay.banking.application.port.in.UpdateFirmBankingUseCase;
import com.JHPay.banking.domain.FirmBankingRequest;
import com.JHPay.common.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final RequestFirmBankingUseCase requestFirmBankingUseCase;
    private final UpdateFirmBankingUseCase updateFirmBankingUseCase;

    @PostMapping(path = "/banking/firmbanking/request")
    @Operation(summary = "펌 뱅킹 요청", description = "펌 뱅킹 요청")
    FirmBankingRequest registerMembership(@RequestBody RegisterFirmBankingRequest request) {
        System.out.println(request);
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankName())
                .toBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        return requestFirmBankingUseCase.requestFirmBanking(command);
    }

    @PostMapping(path = "/banking/firmbanking/request-eda")
    @Operation(summary = "Event 펌 뱅킹 요청", description = "Event 펌 뱅킹 요청")
    void registerMembershipByEvent(@RequestBody RegisterFirmBankingRequest request) {
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankName())
                .toBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        requestFirmBankingUseCase.requestFirmBankingByEvent(command);
    }

    @PutMapping(path = "/banking/firmbanking/update-eda")
    @Operation(summary = "Event 펌 뱅킹 요청", description = "Event 펌 뱅킹 요청")
    void updateMembershipByEvent(@RequestBody UpdateFirmBankingRequest request) {
        UpdateFirmBankingCommand command = UpdateFirmBankingCommand.builder()
                .firmBankingAggregateIdentifier(request.getFirmBankingRequestAggregateIdentifier())
                .status(request.getStatus())
                .build();

        updateFirmBankingUseCase.updateFirmBankingByEvent(command);
    }
}
