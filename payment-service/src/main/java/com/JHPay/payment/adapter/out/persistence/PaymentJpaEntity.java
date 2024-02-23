package com.JHPay.payment.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "payment_reqeust")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentJpaEntity {

    @Id
    @GeneratedValue
    private Long paymentId;

    private String membershipId;

    private int requestPrice;

    private String franchiseId;

    private String franchiseFeeRate;

    private int paymentStatus;

    private Date approvedAt;

    public PaymentJpaEntity(String membershipId, int requestPrice, String franchiseId, String franchiseFeeRate, int paymentStatus, Date approvedAt) {
        this.membershipId = membershipId;
        this.requestPrice = requestPrice;
        this.franchiseId = franchiseId;
        this.franchiseFeeRate = franchiseFeeRate;
        this.paymentStatus = paymentStatus;
        this.approvedAt = approvedAt;
    }
}
