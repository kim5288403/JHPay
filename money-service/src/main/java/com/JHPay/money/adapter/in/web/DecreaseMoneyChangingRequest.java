package com.JHPay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseMoneyChangingRequest {
    // 증액 요청(충전)
    private String targetMembershipId;

    private int amount;
}
