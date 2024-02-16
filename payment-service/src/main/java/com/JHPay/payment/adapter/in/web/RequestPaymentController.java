package com.JHPay.payment.adapter.in.web;

import com.JHPay.common.WebAdapter;
import com.JHPay.payment.application.port.in.RequestPaymentCommand;
import com.JHPay.payment.application.port.in.RequestPaymentUseCase;
import com.JHPay.payment.application.port.out.GetMembershipPort;
import com.JHPay.payment.application.port.out.GetRegisteredBankAccountPort;
import com.JHPay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {

    private final RequestPaymentUseCase requestPaymentUseCase;


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
}