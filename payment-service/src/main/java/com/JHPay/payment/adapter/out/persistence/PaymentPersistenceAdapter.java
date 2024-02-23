package com.JHPay.payment.adapter.out.persistence;

import com.JHPay.common.PersistenceAdapter;
import com.JHPay.payment.application.port.out.CreatePaymentPort;
import com.JHPay.payment.application.port.out.FinishSettlementPort;
import com.JHPay.payment.application.port.out.GetNormalStatusPaymentsPort;
import com.JHPay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements CreatePaymentPort, GetNormalStatusPaymentsPort, FinishSettlementPort {

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

    @Override
    public List<Payment> getNormalStatusPayments() {
        List<PaymentJpaEntity> paymentJpaEntities = repository.findByPaymentStatus(0);

        List<Payment> payments = new ArrayList<>();

        if (paymentJpaEntities != null) {
            for (PaymentJpaEntity entity : paymentJpaEntities) {
                System.out.println(entity);
                payments.add(mapper.mapToDomainEntity(entity));
            }

            return payments;
        }

        return null;
    }

    @Override
    public void changePaymentRequestStatus(String paymentId, int status) {
        Optional<PaymentJpaEntity> paymentJpaEntity = repository.findById(Long.parseLong(paymentId));

        if (paymentJpaEntity.isPresent()) {
            paymentJpaEntity.get().setPaymentStatus(status);
            repository.save(paymentJpaEntity.get());
        }

    }
}
