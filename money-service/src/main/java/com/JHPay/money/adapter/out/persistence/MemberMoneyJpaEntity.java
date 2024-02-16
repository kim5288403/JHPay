package com.JHPay.money.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_money")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyJpaEntity {

    @Id
    @GeneratedValue
    private Long memberMoneyId;

    private String membershipId;

    private int balance;

    private String aggregateIdentifier;

    public MemberMoneyJpaEntity(String membershipId, int balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    @Override
    public String toString() {
        return "MemberMoneyJpaEntity{" +
                "memberMoneyId=" + memberMoneyId +
                ", membershipId='" + membershipId + '\'' +
                ", balance=" + balance +
                ", aggregateIdentifier=" + aggregateIdentifier +
                '}';
    }
}
