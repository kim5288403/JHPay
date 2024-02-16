package com.JHPay.payment.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.payment.application.port.in.RequestPaymentCommand;
import com.JHPay.payment.application.port.in.RequestPaymentUseCase;
import com.JHPay.payment.application.port.out.CreatePaymentPort;
import com.JHPay.payment.application.port.out.GetMembershipPort;
import com.JHPay.payment.application.port.out.GetRegisteredBankAccountPort;
import com.JHPay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class PaymentService implements RequestPaymentUseCase {

    private final CreatePaymentPort createPaymentPort;

    private final GetMembershipPort getMembershipPort;

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
}
