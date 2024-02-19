package com.JHPay.money.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.money.application.port.out.GetMemberMoneyListPort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyFoundRequestPersistenceAdapter implements GetMemberMoneyListPort {

    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public List<MemberMoneyJpaEntity> getMemberMoneyList(List<String> membershipIds) {
        return  memberMoneyRepository.findMemberMoneyListByMembershipIds(membershipIds);
    }
}
