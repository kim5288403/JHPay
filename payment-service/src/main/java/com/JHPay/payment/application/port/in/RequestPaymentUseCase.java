package com.JHPay.payment.application.port.in;

import com.JHPay.payment.domain.Payment;

public interface RequestPaymentUseCase {

    Payment requestPayment(RequestPaymentCommand command);
}
