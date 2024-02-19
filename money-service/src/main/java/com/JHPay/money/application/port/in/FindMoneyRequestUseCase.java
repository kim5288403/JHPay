package com.JHPay.money.application.port.in;

import com.JHPay.money.domain.MemberMoney;

import java.util.List;

public interface FindMoneyRequestUseCase {

    List<MemberMoney> findMemberMoneyListByMembershipIds (FindMemberMoneyListByMembershipIdsCommand command);
}
