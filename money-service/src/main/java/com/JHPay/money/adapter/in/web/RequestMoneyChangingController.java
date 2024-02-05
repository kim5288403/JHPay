package com.JHPay.money.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.JHPay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.JHPay.money.domain.MoneyChangingRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
//    private final DecreaseMoneyRequestUseCase decreaseMoneyRequestUseCase;


    @PostMapping(path = "/money/increase")
    @Operation(summary = "증액 요청", description = "증액 요청")
    MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand
                .builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        MoneyChangingResultDetail moneyChangingResultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0,
                moneyChangingRequest.getChangingMoneyAmount(),
                0
        );

        return moneyChangingResultDetail;
    }

    @PostMapping(path = "/money/decrease")
    @Operation(summary = "감액 요청", description = "감액 요청")
    MoneyChangingResultDetail decreaseMoneyChangingRequest(@RequestBody  DecreaseMoneyChangingRequest request) {
//        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
//                .membershipId(request.getMembershipId())
//                .bankName(request.getBankName())
//                .bankAccountNumber(request.getBankAccountNumber())
//                .isValid(request.isValid())
//                .build();

        return null;
    }
}
