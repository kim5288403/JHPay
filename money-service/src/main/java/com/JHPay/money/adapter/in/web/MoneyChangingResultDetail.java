package com.JHPay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyChangingResultDetail {

    private String moneyChangingRequestId;

    private int moneyChangingType;

    private int amount;

    private int changingMoneyStatus;

}

//enum MoneyChangingType {
//    INCREASING, // 증액
//    DECREASING // 감액
//}
//
//enum MoneyChangingResultStatus {
//    SUCCEEDED, // 성공
//    FAILED, // 실패
//    FAILED_NOT_ENOUGH_MONEY, // 잔액 부족
//    FAILED_NOT_EXIST_MEMBERSHIP,// 맴버십 없음
//    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST, // 머니 변액 요청 없음
//}
