package com.JHPay.bankingservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJapEntity, Long> {
}
