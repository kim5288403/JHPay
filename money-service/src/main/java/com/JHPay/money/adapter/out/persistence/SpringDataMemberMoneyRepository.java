package com.JHPay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    MemberMoneyJpaEntity findByMembershipId(String membershipId);

    @Query("SELECT e FROM MemberMoneyJpaEntity e WHERE e.membershipId in :membershipIds")
    List<MemberMoneyJpaEntity> findMemberMoneyListByMembershipIds(@Param("membershipIds") List<String> membershipIds);
}
