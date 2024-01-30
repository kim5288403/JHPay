package com.JHPay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

    private final String membershipId;

    private final String name;

    private final String email;

    private final String address;

    private final boolean isValid;

    private final boolean isCorp;

    // Membership
    // 오염이 되면 안되는 클래스, 고객 정보, 핵심 도메인

    public static Membership generateMember(
            MembershipId membershipId,
            MembershipName membershipName,
            MembershipEmail membershipIdEmail,
            MembershipAddress membershipIdAddress,
            MembershipIsValid membershipIsValid,
            MembershipIsCorp membershipIsCorp
    ) {
        return new Membership(
                membershipId.membershipId,
                membershipName.membershipName,
                membershipIdEmail.membershipEmail,
                membershipIdAddress.membershipAddress,
                membershipIsValid.membershipIsValid,
                membershipIsCorp.membershipIsCorp
        );
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }

        String membershipId;
    }

    @Value
    public static class MembershipName {
        public MembershipName(String value) {
            this.membershipName = value;
        }

        String membershipName;
    }

    @Value
    public static class MembershipEmail {
        public MembershipEmail(String value) {
            this.membershipEmail = value;
        }

        String membershipEmail;
    }

    @Value
    public static class MembershipAddress {
        public MembershipAddress(String value) {
            this.membershipAddress = value;
        }

        String membershipAddress;
    }

    @Value
    public static class MembershipIsValid {
        public MembershipIsValid(boolean value) {
            this.membershipIsValid = value;
        }

        boolean membershipIsValid;
    }

    @Value
    public static class MembershipIsCorp {
        public MembershipIsCorp(boolean value) {
            this.membershipIsCorp = value;
        }

        boolean membershipIsCorp;
    }

}
