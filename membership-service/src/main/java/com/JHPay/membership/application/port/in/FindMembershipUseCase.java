package com.JHPay.membership.application.port.in;

import com.JHPay.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
