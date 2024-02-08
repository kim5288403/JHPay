package com.JHPay.remittance.application.port.out;

import com.JHPay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.JHPay.remittance.application.port.in.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {

    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}