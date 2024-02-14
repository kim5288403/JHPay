package com.JHPay.money.application.port.in;

import com.JHPay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.JHPay.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(
            MemberMoney.MembershipId membershipId
    );
}
