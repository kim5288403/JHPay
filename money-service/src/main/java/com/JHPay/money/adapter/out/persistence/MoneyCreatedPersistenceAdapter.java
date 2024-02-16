package com.JHPay.money.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.money.application.port.in.CreateMemberMoneyPort;
import com.JHPay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyCreatedPersistenceAdapter implements CreateMemberMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;


    @Override
    public void createMemberMoney(
            MemberMoney.MembershipId membershipId,
            MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
    ) {
        MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
                membershipId.getMembershipId(),
                0,
                aggregateIdentifier.getMoneyAggregateIdentifier()
        );

        memberMoneyRepository.save(entity);
    }
}
