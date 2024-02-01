package com.JHPay.membership.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.membership.adapter.out.persistence.MembershipJapEntity;
import com.JHPay.membership.adapter.out.persistence.MembershipMapper;
import com.JHPay.membership.application.port.in.ModifyMembershipCommand;
import com.JHPay.membership.application.port.in.ModifyMembershipUseCase;
import com.JHPay.membership.application.port.out.ModifyMembershipPort;
import com.JHPay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;


@UseCase
@Transactional
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase
{

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJapEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
