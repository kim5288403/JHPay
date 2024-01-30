package com.JHPay.membership.application.port.out;

import com.JHPay.membership.adapter.out.persistence.MembershipJapEntity;
import com.JHPay.membership.domain.Membership;

public interface FindMembershipPort {

    MembershipJapEntity findMembership(
            Membership.MembershipId membershipId
    );
}
