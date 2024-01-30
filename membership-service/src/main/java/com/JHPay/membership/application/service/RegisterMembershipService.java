package com.JHPay.membership.application.service;

import com.JHPay.membership.adapter.out.persistence.MembershipJapEntity;
import com.JHPay.membership.adapter.out.persistence.MembershipMapper;
import com.JHPay.membership.application.port.in.RegisterMembershipCommand;
import com.JHPay.membership.application.port.in.RegisterMembershipUseCase;
import com.JHPay.membership.application.port.out.RegisterMembershipPort;
import com.JHPay.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;


@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;
    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipJapEntity jpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
