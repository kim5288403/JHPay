package com.JHPay.payment.application.port.out;

import com.JHPay.payment.domain.Payment;

import java.util.List;

public interface GetNormalStatusPaymentsPort {
    List<Payment> getNormalStatusPayments();
}
