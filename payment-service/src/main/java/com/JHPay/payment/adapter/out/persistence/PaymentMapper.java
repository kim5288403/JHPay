package com.JHPay.payment.adapter.out.persistence;

import com.JHPay.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment mapToDomainEntity(PaymentJpaEntity entity) {
        return Payment.generatePayment(
                new Payment.PaymentId(entity.getPaymentId()),
                new Payment.MembershipId(entity.getMembershipId()),
                new Payment.RequestPrice(entity.getRequestPrice()),
                new Payment.FranchiseId(entity.getFranchiseId()),
                new Payment.FranchiseFeeRate(entity.getFranchiseFeeRate()),
                new Payment.PaymentStatus(entity.getPaymentStatus()),
                new Payment.ApprovedAt(entity.getApprovedAt())
        );
    }
}
