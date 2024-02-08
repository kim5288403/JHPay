package com.JHPay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    MemberMoneyJpaEntity findByMembershipId(String membershipId);
}