package com.JHPay.banking.adapter.out.persistence;

import com.JHPay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.JHPay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.JHPay.banking.application.port.out.RegisterBankAccountPort;
import com.JHPay.banking.domain.RegisteredBankAccount;
import com.JHPay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort, GetRegisteredBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository repository;
    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
            RegisteredBankAccount.AggregateIdentifier aggregateIdentifier
            ) {
        System.out.println("membershipId : " + membershipId.getMembershipId());
        System.out.println("bankName : " + bankName.getBankName());
        System.out.println("bankAccountNumber : " + bankAccountNumber.getBankAccountNumber());
        System.out.println("linkedStatusIsValid : " + linkedStatusIsValid.isLinkedStatusIsValid());
        System.out.println("aggregateIdentifier : " + aggregateIdentifier.getAggregateIdentifier());

        RegisteredBankAccountJpaEntity entity = RegisteredBankAccountJpaEntity
                .builder()
                .membershipId(membershipId.getMembershipId())
                .bankName(bankName.getBankName())
                .linkedStatusIsValid(linkedStatusIsValid.isLinkedStatusIsValid())
                .aggregateIdentifier(aggregateIdentifier.getAggregateIdentifier())
                .bankAccountNumber(bankAccountNumber.getBankAccountNumber())
                .build();

        System.out.println(entity.toString());

        return repository.save(entity);
    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return repository.findByMembershipId(command.getMembershipId());
    }
}
