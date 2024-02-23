package com.JHPay.membership.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.membership.adapter.out.vault.VaultAdapter;
import com.JHPay.membership.application.port.out.FindMembershipPort;
import com.JHPay.membership.application.port.out.ModifyMembershipPort;
import com.JHPay.membership.application.port.out.RegisterMembershipPort;
import com.JHPay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;
    private final VaultAdapter vaultAdapter;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        // email 만 암호화 해보는 것으로 결정.
        String encryptedEmail = vaultAdapter.encryptData(membershipEmail.getMembershipEmail());

        MembershipJpaEntity jpaEntity = new MembershipJpaEntity(
                membershipName.getMembershipName(),
                membershipAddress.getMembershipAddress(),
                encryptedEmail,
                membershipIsValid.isMembershipIsValid(),
                membershipIsCorp.isMembershipIsCorp(),
                ""
        );
        membershipRepository.save(jpaEntity);

        MembershipJpaEntity clone = jpaEntity.clone();
        clone.setEmail(membershipEmail.getMembershipEmail());

        return clone;
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        Optional<MembershipJpaEntity> membershipJpaEntity = membershipRepository.findById(Long.parseLong(membershipId.getMembershipId()));

        if (membershipJpaEntity.isPresent()) {
            String encryptedEmailString = membershipJpaEntity.get().getEmail();
            String decryptedEmailString = vaultAdapter.decryptData(encryptedEmailString);

            MembershipJpaEntity clone = membershipJpaEntity.get().clone();
            clone.setEmail(decryptedEmailString);
            return clone;
        }

        return null;
    }

    @Override
    public List<MembershipJpaEntity> findMembershipListByAddress(Membership.MembershipAddress membershipAddress) {
        // 관악구, 서초구, 강남구 중 하나
        String address = membershipAddress.getMembershipAddress();
        List<MembershipJpaEntity> membershipJpaEntityList = membershipRepository.findByAddress(address);
        List<MembershipJpaEntity> cloneList = new ArrayList<>();

        for (MembershipJpaEntity membershipJpaEntity : membershipJpaEntityList) {
            String encryptedEmailString = membershipJpaEntity.getEmail();
            String decryptedEmailString = vaultAdapter.decryptData(encryptedEmailString);

            MembershipJpaEntity clone = membershipJpaEntity.clone();
            clone.setEmail(decryptedEmailString);
            cloneList.add(clone);
        }

        return cloneList;
    }

    @Override
    public MembershipJpaEntity modifyMembership(
            Membership.MembershipId membershipId,
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp,
            Membership.RefreshToken refreshToken)
    {
        Optional<MembershipJpaEntity> entity = membershipRepository.findById(Long.parseLong(membershipId.getMembershipId()));

        if (entity.isPresent()) {
            // email 만 암호화 해보는 것으로 결정.
            String encryptedEmail = vaultAdapter.encryptData(membershipEmail.getMembershipEmail());
            MembershipJpaEntity findEntity = entity.get();
            findEntity.setName(membershipName.getMembershipName());
            findEntity.setAddress(membershipAddress.getMembershipAddress());
            findEntity.setEmail(encryptedEmail);
            findEntity.setCorp(membershipIsCorp.isMembershipIsCorp());
            findEntity.setValid(membershipIsValid.isMembershipIsValid());
            findEntity.setRefreshToken(refreshToken.getRefreshToken());
            membershipRepository.save(findEntity);

            // Todo 리턴 전에 새로운 객체로 평문화된 멤버 정보를 리턴해 줘야 해요.
            MembershipJpaEntity clone = findEntity.clone();
            clone.setEmail(membershipEmail.getMembershipEmail());
            return clone;
        }

        return null;
    }
}
