package com.JHPay.payment.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.payment.application.port.in.*;
import com.JHPay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {

    private final RequestPaymentUseCase requestPaymentUseCase;
    private final GetNormalStatusPaymentsUseCase getNormalStatusPaymentsUseCase;
    private final FinishSettlementUseCase finishSettlementUseCase;


    @PostMapping(path = "/payment/request")
    public Payment requestPayment(PaymentRequest request) {
        return requestPaymentUseCase.requestPayment(
                new RequestPaymentCommand(
                        request.getMembershipId(),
                        request.getRequestPrise(),
                        request.getFranchiseId(),
                        request.getFranchiseFeeRate()
                        )
        );
    }

    @GetMapping(path = "/payment/normal-status")
    public List<Payment> getNormalStatusPayments() {
        return getNormalStatusPaymentsUseCase.getNormalStatusPayments();
    }

    @PostMapping(path = "/payment/finish-settlement")
    public void finishSettlement(FinishSettlementRequest request) {
        System.out.println(request.getPaymentId());
        finishSettlementUseCase.finishPayment(
                new FinishSettlementCommand(request.getPaymentId())
        );
    }
}