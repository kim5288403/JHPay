package com.JHPay.moneyAggregation.application.port.in;

public interface GetMoneySumByAddressUseCase {
    int getMoneySumByAddress(GetMoneySumByAddressCommand command);
}
