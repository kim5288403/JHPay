package com.JHPay.money.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.JHPay.money.adapter.out.persistence.MemberMoneyMapper;
import com.JHPay.money.application.port.in.FindMemberMoneyListByMembershipIdsCommand;
import com.JHPay.money.application.port.in.FindMoneyRequestUseCase;
import com.JHPay.money.application.port.out.GetMemberMoneyListPort;
import com.JHPay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@UseCase
@Transactional
@RequiredArgsConstructor
public class findMoneyRequestService implements FindMoneyRequestUseCase {

    private final GetMemberMoneyListPort getMemberMoneyListPort;
    private final MemberMoneyMapper mapper;

    @Override
    public List<MemberMoney> findMemberMoneyListByMembershipIds(FindMemberMoneyListByMembershipIdsCommand command) {
        // 여러개의 맴버쉽 아이를 기준으로 맴버머니 정보를 가져와야함
        List<MemberMoneyJpaEntity> list = getMemberMoneyListPort.getMemberMoneyList(command.getMembershipId());
        System.out.println(command.getMembershipId().get(0));
        System.out.println(list.get(0));
        List<MemberMoney> memberMoneys = new ArrayList<>();
        for (MemberMoneyJpaEntity memberMoneyJpaEntity : list) {
            memberMoneys.add(mapper.mapToDomainEntity(memberMoneyJpaEntity));
        }

        return memberMoneys;
    }
}

// AKIAR62GP44ZSRS5GMVP
// 7rsA/0gSZd1AkY7uTSaBHG1Z3UayzGvolTQFtKxo
