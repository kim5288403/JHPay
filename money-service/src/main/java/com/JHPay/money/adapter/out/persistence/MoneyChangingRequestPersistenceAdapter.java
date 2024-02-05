package com.JHPay.money.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.money.application.port.out.IncreaseMoneyPort;
import com.JHPay.money.domain.MemberMoney;
import com.JHPay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.ChangingType changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingStatus changingStatus,
            MoneyChangingRequest.Uuid uuid) {
        return moneyChangingRequestRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        changingType.getChangingType(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        new Date(System.currentTimeMillis()),
                        changingStatus.getChangingMoneyStatus(),
                        UUID.randomUUID().toString()
                )
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId membershipId,
            int increaseMoneyAmount
    ) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.findByMembershipId(membershipId.getMembershipId());
        if (entity == null) {
            entity = new MemberMoneyJpaEntity(
                    membershipId.getMembershipId(),
                    increaseMoneyAmount
            );

        } else {
            entity.setBalance(entity.getBalance() + increaseMoneyAmount);
        }


        return memberMoneyRepository.save(entity);
    }
}
