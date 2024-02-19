package com.JHPay.money.application.port.out;

import com.JHPay.money.adapter.out.persistence.MemberMoneyJpaEntity;

import java.util.List;

public interface GetMemberMoneyListPort {

    List<MemberMoneyJpaEntity> getMemberMoneyList(List<String> membershipIds);
}
