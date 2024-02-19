package com.JHPay.money.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.money.application.port.in.CreateMemberMoneyCommand;
import com.JHPay.money.application.port.in.CreateMemberMoneyUseCase;
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
    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;
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

        return new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0,
                moneyChangingRequest.getChangingMoneyAmount(),
                0
        );
    }

    @PostMapping(path = "/money/increase-async")
    @Operation(summary = "비동기 증액 요청", description = "비동기 증액 요청")
    MoneyChangingResultDetail increaseMoneyChangingRequestAsync(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand
                .builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequestAsync(command);

        return new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0,
                moneyChangingRequest.getChangingMoneyAmount(),
                0
        );
    }

    @PostMapping(path = "/money/increase-eda")
    @Operation(summary = "Event 증액 요청", description = "Event 증액 요청")
    void increaseMoneyChangingRequestByEvent(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand
                .builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        increaseMoneyRequestUseCase.increaseMoneyRequestByEvent(command);

    }

    @PostMapping(path = "/money/create-member-money")
    @Operation(summary = "EDA 맴버 머니 생성", description = "EDA 맴버 머니 생성")
    void createMemberMoney (@RequestBody CreateMemberMoneyRequest request) {
        createMemberMoneyUseCase
                .createMemberMoney(
                        CreateMemberMoneyCommand.builder()
                                .membershipId(request.getMembershipId())
                                .build()
                );
    }

    @PostMapping(path = "/money/decrease-eda")
    @Operation(summary = "Event 감액 요청", description = "Event 감액 요청")
    void decreaseMoneyChangingRequestByEvent(@RequestBody  DecreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand
                .builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount() * -1)
                .build();

        increaseMoneyRequestUseCase.increaseMoneyRequestByEvent(command);
    }




}
