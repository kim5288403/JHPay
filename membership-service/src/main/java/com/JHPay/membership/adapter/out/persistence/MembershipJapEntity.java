package com.JHPay.membership.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipJapEntity {

    @Id
    @GeneratedValue
    private Long membershipId;

    private String name;

    private String address;

    private String email;

    private boolean isValid;

    private boolean isCorp;

    @Override
    public String toString() {
        return "MembershipJapEntity{" +
                "membershipId=" + membershipId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", isValid=" + isValid +
                ", isCorp=" + isCorp +
                '}';
    }

    public MembershipJapEntity(String name, String address, String email, boolean isValid, boolean isCorp) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.isValid = isValid;
        this.isCorp = isCorp;
    }
}
