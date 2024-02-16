package com.JHPay.payment.application.port.out;

import com.JHPay.payment.domain.Payment;

public interface CreatePaymentPort {
    Payment CreatePayment(
            String membershipId,
            String requestPrice,
            String franchiseId,
            String franchiseFeeRate
            );
}
