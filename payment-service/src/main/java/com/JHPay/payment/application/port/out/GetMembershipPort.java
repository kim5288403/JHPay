package com.JHPay.payment.application.port.out;

public interface GetMembershipPort {

    MembershipStatus getMembership(String membershipId);
}
