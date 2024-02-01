package com.JHPay.membership.application.port.in;

import com.JHPay.membership.domain.Membership;

public interface RegisterMembershipUseCase {

    Membership registerMembership(RegisterMembershipCommand command);
}
