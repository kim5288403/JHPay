package com.JHPay.membership.application.port.out;

import com.JHPay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.JHPay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipPort {

    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );

    List<MembershipJpaEntity> findMembershipListByAddress(
            Membership.MembershipAddress membershipAddress
    );
}
