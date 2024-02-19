package com.JHPay.membership.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.JHPay.membership.adapter.out.persistence.MembershipMapper;
import com.JHPay.membership.application.port.in.FindMembershipCommand;
import com.JHPay.membership.application.port.in.FindMembershipListByAddressCommand;
import com.JHPay.membership.application.port.in.FindMembershipUseCase;
import com.JHPay.membership.application.port.out.FindMembershipPort;
import com.JHPay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity jpaEntity = findMembershipPort.findMembership(
                new Membership.MembershipId(command.getMembershipId())
        );
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }

    @Override
    public List<Membership> findMembershipListByAddress(FindMembershipListByAddressCommand command) {
        List<MembershipJpaEntity> list = findMembershipPort.findMembershipListByAddress(new Membership.MembershipAddress(command.getAddressName()));
        List<Membership> memberships = new ArrayList<>();
        for (MembershipJpaEntity  membershipJpaEntity : list) {
            memberships.add(membershipMapper.mapToDomainEntity(membershipJpaEntity));
        }

        return memberships;
    }
}
