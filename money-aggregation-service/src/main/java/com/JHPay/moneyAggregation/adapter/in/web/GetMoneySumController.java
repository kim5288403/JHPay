package com.JHPay.moneyAggregation.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.moneyAggregation.application.port.in.GetMoneySumByAddressCommand;
import com.JHPay.moneyAggregation.application.port.in.GetMoneySumByAddressUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetMoneySumController {

    private final GetMoneySumByAddressUseCase getMoneySumByAddressUseCase;

    @PostMapping(path = "/money/aggregation/sum")
    @Operation(summary = "지역 금액 합", description = "지역 금액 합")
    int getMoneySumByAddress(@RequestBody GetMoneySumByAddressRequest request) {
        GetMoneySumByAddressCommand command = GetMoneySumByAddressCommand
                .builder()
                .address(request.getAddress())
                .build();

        return getMoneySumByAddressUseCase.getMoneySumByAddress(command);
    }


}
