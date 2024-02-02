package com.JHPay.bankingservice.adapter.out.persistence;

import com.JHPay.bankingservice.application.port.out.RegisterBankAccountPort;
import com.JHPay.bankingservice.domain.RegisteredBankAccount;
import com.JHPay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository repository;
    @Override
    public RegisteredBankAccountJapEntity createRegisteredBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return repository.save(
                new RegisteredBankAccountJapEntity(
                        membershipId.getMembershipId(),
                        bankName.getBankName(),
                        bankAccountNumber.getBankAccountNumber(),
                        linkedStatusIsValid.isLinkedStatusIsValid()
                )
        );
    }
}
