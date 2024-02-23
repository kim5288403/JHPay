package com.JHPay.payment.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.payment.application.port.in.*;
import com.JHPay.payment.application.port.out.*;
import com.JHPay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class PaymentService implements RequestPaymentUseCase, GetNormalStatusPaymentsUseCase, FinishSettlementUseCase {

    private final CreatePaymentPort createPaymentPort;

    private final GetMembershipPort getMembershipPort;

    private final GetNormalStatusPaymentsPort getNormalStatusPaymentsPort;

    private final FinishSettlementPort finishSettlementPort;

    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    // todo Money Service -> member Money 정보를 가져오기
    @Override
    public Payment requestPayment(RequestPaymentCommand command) {

        // 충전, 맴버쉽, 유효성 검사 등

        // getMembershipPort.getMembership(command.getMembershipId());

        // getRegisteredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());

        return createPaymentPort.CreatePayment(
                command.getMembershipId(),
                command.getRequestPrise(),
                command.getFranchiseId(),
                command.getFranchiseFeeRate()
        );
    }

    @Override
    public List<Payment> getNormalStatusPayments() {
        return getNormalStatusPaymentsPort.getNormalStatusPayments();
    }

    @Override
    public void finishPayment(FinishSettlementCommand command) {
        finishSettlementPort.changePaymentRequestStatus(command.getPaymentId(), 2);
    }
}
