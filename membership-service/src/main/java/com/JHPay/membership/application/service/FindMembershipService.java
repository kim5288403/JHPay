package com.JHPay.membership.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.membership.adapter.out.persistence.MembershipJapEntity;
import com.JHPay.membership.adapter.out.persistence.MembershipMapper;
import com.JHPay.membership.application.port.in.FindMembershipCommand;
import com.JHPay.membership.application.port.in.FindMembershipUseCase;
import com.JHPay.membership.application.port.out.FindMembershipPort;
import com.JHPay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJapEntity jpaEntity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
