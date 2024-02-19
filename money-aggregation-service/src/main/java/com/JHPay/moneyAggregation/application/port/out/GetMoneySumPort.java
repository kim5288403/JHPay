package com.JHPay.moneyAggregation.application.port.out;

import java.util.List;

public interface GetMoneySumPort {

    List<MemberMoney> getMoneySumByMembershipIds(List<String> membershipIds);
}
