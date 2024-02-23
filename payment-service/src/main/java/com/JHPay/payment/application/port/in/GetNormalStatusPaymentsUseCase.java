package com.JHPay.payment.application.port.in;

import com.JHPay.payment.domain.Payment;

import java.util.List;

public interface GetNormalStatusPaymentsUseCase {

    // todo
    // command between date
    List<Payment> getNormalStatusPayments();
}
