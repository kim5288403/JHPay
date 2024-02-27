package com.JHPay.remittance.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.remittance.application.port.in.FindRemittanceCommand;
import com.JHPay.remittance.application.port.in.FindRemittanceUseCase;
import com.JHPay.remittance.domain.RemittanceRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindRemittanceHistoryController {

    private final FindRemittanceUseCase findRemittanceUseCase;
    @GetMapping( "/remittance/{membershipId}")
    @Operation(summary = "송금 요청 내역", description = "송금 요청 내역 가져오기")
    List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
        FindRemittanceCommand command = FindRemittanceCommand.builder()
                .membershipId(membershipId)
                .build();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return findRemittanceUseCase.findRemittanceHistory(command);
    }
}