package com.JHPay.membership.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.membership.application.port.out.FindMembershipPort;
import com.JHPay.membership.application.port.out.ModifyMembershipPort;
import com.JHPay.membership.application.port.out.RegisterMembershipPort;
import com.JHPay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;
    @Override
    public MembershipJapEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return membershipRepository.save(
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
        return membershipRepository.findById(Long.valueOf(membershipId.getMembershipId())).orElse(null);
    }

    @Override
    public MembershipJapEntity modifyMembership(
            Membership.MembershipId membershipId,
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp)
    {
        MembershipJapEntity entity = membershipRepository.findById(Long.valueOf(membershipId.getMembershipId())).orElse(null);

        assert entity != null;
        entity.setName(membershipName.getMembershipName());
        entity.setEmail(membershipEmail.getMembershipEmail());
        entity.setAddress(membershipAddress.getMembershipAddress());
        entity.setValid(membershipIsValid.isMembershipIsValid());
        entity.setCorp(membershipIsCorp.isMembershipIsCorp());

        return membershipRepository.save(entity);
    }
}
