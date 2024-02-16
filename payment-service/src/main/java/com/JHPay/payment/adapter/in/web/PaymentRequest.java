package com.JHPay.payment.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private String membershipId;

    private String requestPrise;

    private String franchiseId;

    private String franchiseFeeRate;
//
//    private int paymentStatus;
//
//    private Date approvedAt;
}
