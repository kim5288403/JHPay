package com.JHPay.membership.application.port.out;

import com.JHPay.membership.adapter.out.persistence.MembershipJapEntity;
import com.JHPay.membership.domain.Membership;

public interface ModifyMembershipPort {

    MembershipJapEntity modifyMembership(
            Membership.MembershipId membershipId,
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    );
}
