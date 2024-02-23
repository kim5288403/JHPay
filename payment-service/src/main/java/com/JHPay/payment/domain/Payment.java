package com.JHPay.payment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {
    private Long paymentId;

    private String membershipId;

    private int requestPrice;

    private String franchiseId;

    private String franchiseFeeRate;

    private int paymentStatus;

    private Date approvedAt;

    public static Payment generatePayment(
                PaymentId paymentId,
                MembershipId membershipId,
                RequestPrice requestPrice,
                FranchiseId franchiseId,
                FranchiseFeeRate franchiseFeeRate,
                PaymentStatus PaymentId,
                ApprovedAt approvedAt
            ) {
        return new Payment(
                paymentId.getPaymentId(),
                membershipId.getMembershipId(),
                requestPrice.getRequestPrice(),
                franchiseId.getFranchiseId(),
                franchiseFeeRate.getFranchiseFeeRate(),
                PaymentId.getPaymentStatus(),
                approvedAt.getApprovedAt()
        );
    }

    @Value
    public static class PaymentId {
        public PaymentId(Long value) {
            this.paymentId = value;
        }

        Long paymentId;
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }

        String membershipId;
    }

    @Value
    public static class RequestPrice {
        public RequestPrice(int value) {
            this.requestPrice = value;
        }

        int requestPrice;
    }

    @Value
    public static class FranchiseId {
        public FranchiseId(String value) {
            this.franchiseId = value;
        }

        String franchiseId;
    }

    @Value
    public static class FranchiseFeeRate {
        public FranchiseFeeRate(String value) {
            this.franchiseFeeRate = value;
        }

        String franchiseFeeRate;
    }

    @Value
    public static class PaymentStatus {
        public PaymentStatus(int value) {
            this.paymentStatus = value;
        }

        int paymentStatus;
    }

    @Value
    public static class ApprovedAt {
        public ApprovedAt(Date value) {
            this.approvedAt = value;
        }

        Date approvedAt;
    }




}
