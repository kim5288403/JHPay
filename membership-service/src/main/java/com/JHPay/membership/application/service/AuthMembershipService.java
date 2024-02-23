package com.JHPay.membership.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.JHPay.membership.adapter.out.persistence.MembershipMapper;
import com.JHPay.membership.application.port.in.*;
import com.JHPay.membership.application.port.out.AuthMembershipPort;
import com.JHPay.membership.application.port.out.FindMembershipPort;
import com.JHPay.membership.application.port.out.ModifyMembershipPort;
import com.JHPay.membership.domain.JwtToken;
import com.JHPay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AuthMembershipService implements AuthMembershipUseCase {

    private final AuthMembershipPort authMembershipPort;
    private final FindMembershipPort findMembershipPort;
    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper mapper;

    @Override
    public JwtToken loginMembership(LoginMembershipCommand command) {

        String membershipId = command.getMembershipId();
        MembershipJpaEntity membershipJpaEntity = findMembershipPort.findMembership(
                new Membership.MembershipId(membershipId)
        );

        if (membershipJpaEntity.isValid()) {
            String accessToken = authMembershipPort.generateAccessToken(new Membership.MembershipId(membershipId));
            String refreshToken = authMembershipPort.generateRefreshToken(new Membership.MembershipId(membershipId));

            modifyMembershipPort.modifyMembership(
                    new Membership.MembershipId(membershipId),
                    new Membership.MembershipName(membershipJpaEntity.getName()),
                    new Membership.MembershipEmail(membershipJpaEntity.getEmail()),
                    new Membership.MembershipAddress(membershipJpaEntity.getAddress()),
                    new Membership.MembershipIsValid(membershipJpaEntity.isValid()),
                    new Membership.MembershipIsCorp(membershipJpaEntity.isCorp()),
                    new Membership.RefreshToken(refreshToken)
            );

            return JwtToken.generateJwtToken(
                    new JwtToken.MembershipId(membershipId),
                    new JwtToken.AccessToken(accessToken),
                    new JwtToken.RefreshToken(refreshToken)
            );
        }

        return null;
    }

    @Override
    public JwtToken refreshToken(RefreshTokenCommand command) {
        boolean isValidToken = authMembershipPort.validateToken(command.getRefreshToken());

        if (isValidToken) {
            MembershipJpaEntity jpaEntity = findMembershipPort.findMembership(authMembershipPort.parseMembershipIdFromToken(command.getRefreshToken()));
            if (!command.getRefreshToken().equals(jpaEntity.getRefreshToken())) {
                // refresh token not equal err
                return null;
            }

            if (jpaEntity.isValid()){
                String newAccessToken = authMembershipPort.generateAccessToken(new Membership.MembershipId(""+jpaEntity.getMembershipId()));
                return JwtToken.generateJwtToken(
                        new JwtToken.MembershipId(""+jpaEntity.getMembershipId()),
                        new JwtToken.AccessToken(newAccessToken),
                        new JwtToken.RefreshToken(command.getRefreshToken())
                );
            }
        }

        return null;
    }

    @Override
    public boolean validateToken(ValidateTokenCommand command) {
        return authMembershipPort.validateToken(command.getJwtToken());
    }

    @Override
    public Membership getMembershipByAccessToken(GetMembershipByAccessTokenCommand command) {
        String accessToken = command.getAccessToken();
        boolean isValidToken = authMembershipPort.validateToken(accessToken);

        if (isValidToken) {
            Membership.MembershipId membershipId = authMembershipPort.parseMembershipIdFromToken(accessToken);
            MembershipJpaEntity jpaEntity = findMembershipPort.findMembership(membershipId);


            if (jpaEntity.isValid()){
                return mapper.mapToDomainEntity(jpaEntity);
            }
        }

        return null;
    }
}
