package com.JHPay.payment.application.port.out;

public interface FinishSettlementPort {

    void changePaymentRequestStatus(String paymentId, int status);
}
