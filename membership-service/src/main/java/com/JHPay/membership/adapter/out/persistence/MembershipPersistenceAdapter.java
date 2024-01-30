package com.JHPay.membership.adapter.out.persistence;

import com.JHPay.membership.application.port.out.FindMembershipPort;
import com.JHPay.membership.application.port.out.RegisterMembershipPort;
import com.JHPay.membership.domain.Membership;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort {

    private final SpringDataMembershipRepository springDataMembershipRepository;
    @Override
    public MembershipJapEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return springDataMembershipRepository.save(
                new MembershipJapEntity(
                        membershipName.getMembershipName(),
                        membershipEmail.getMembershipEmail(),
                        membershipAddress.getMembershipAddress(),
                        membershipIsValid.isMembershipIsValid(),
                        membershipIsCorp.isMembershipIsCorp()
                )
        );
    }

    @Override
    public MembershipJapEntity findMembership(Membership.MembershipId membershipId) {
        return springDataMembershipRepository.findById(Long.valueOf(membershipId.getMembershipId())).orElse(null);
    }
}
