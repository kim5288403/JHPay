package com.JHPay.banking.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "registered_bank_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredBankAccountJpaEntity {

    @Id
    @GeneratedValue
    private Long registeredBankAccountId;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;

    private boolean linkedStatusIsValid;

    private String aggregateIdentifier;
    @Override
    public String toString() {
        return "RegisteredBankAccountJapEntity{" +
                "registeredBankAccountId=" + registeredBankAccountId +
                ", membershipId=" + membershipId +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                ", aggregateIdentifier=" + aggregateIdentifier +
                '}';
    }

    public RegisteredBankAccountJpaEntity(
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean linkedStatusIsValid,
            String aggregateIdentifier
    ) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.aggregateIdentifier = aggregateIdentifier;
    }
}
