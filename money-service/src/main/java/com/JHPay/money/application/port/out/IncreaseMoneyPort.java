package com.JHPay.money.application.port.out;

import com.JHPay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.JHPay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.JHPay.money.domain.MemberMoney;
import com.JHPay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.ChangingType changingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.ChangingStatus changingStatus,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId membershipId,
            int increaseMoneyAmount
    );
}
