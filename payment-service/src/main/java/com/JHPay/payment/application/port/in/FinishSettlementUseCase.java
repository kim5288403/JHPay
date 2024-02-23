package com.JHPay.payment.application.port.in;

public interface FinishSettlementUseCase {

    void finishPayment(FinishSettlementCommand command);
}
