package com.JHPay.remittance.application.service;

import com.JHPay.common.UseCase;
import com.JHPay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.JHPay.remittance.application.port.in.FindRemittanceCommand;
import com.JHPay.remittance.application.port.in.FindRemittanceUseCase;
import com.JHPay.remittance.application.port.out.FindRemittancePort;
import com.JHPay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {
    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        //
        findRemittancePort.findRemittanceHistory(command);
        return null;
    }
}