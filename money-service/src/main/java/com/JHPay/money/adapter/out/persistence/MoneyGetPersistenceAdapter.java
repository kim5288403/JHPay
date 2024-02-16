package com.JHPay.money.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.money.application.port.in.GetMemberMoneyPort;
import com.JHPay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyGetPersistenceAdapter implements GetMemberMoneyPort {

    private final SpringDataMemberMoneyRepository memberMoneyRepository;


    @Override
    public MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId membershipId) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.findByMembershipId(membershipId.getMembershipId());
        if (entity == null) {
            entity = new MemberMoneyJpaEntity(
                    membershipId.getMembershipId(),
                    0,
                    ""
            );

            return memberMoneyRepository.save(entity);

        }

        return entity;
    }
}
