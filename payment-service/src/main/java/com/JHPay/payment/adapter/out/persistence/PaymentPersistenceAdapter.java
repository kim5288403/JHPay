package com.JHPay.payment.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.payment.application.port.out.CreatePaymentPort;
import com.JHPay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements CreatePaymentPort {

    private final SpringDataPaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    public Payment CreatePayment(String membershipId, String requestPrice, String franchiseId, String franchiseFeeRate) {

        PaymentJpaEntity entity = repository.save(
                new PaymentJpaEntity(
                        membershipId,
                        Integer.parseInt(requestPrice),
                        franchiseId,
                        franchiseFeeRate,
                        0,
                        null
                )
        );

        return mapper.mapToDomainEntity(entity);
    }
}
