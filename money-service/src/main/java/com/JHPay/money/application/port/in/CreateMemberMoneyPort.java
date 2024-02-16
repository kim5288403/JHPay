package com.JHPay.money.application.port.in;

import com.JHPay.money.domain.MemberMoney;

public interface CreateMemberMoneyPort {
    void createMemberMoney(
            MemberMoney.MembershipId membershipId,
            MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
    );
}
