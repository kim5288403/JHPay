package com.JHPay.query.adapter.out.aws.dynamodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneySumByAddress {
    private String PK;

    private String SK;

    private int balance;
}
