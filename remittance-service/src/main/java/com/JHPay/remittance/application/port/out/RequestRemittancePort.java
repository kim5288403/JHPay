package com.JHPay.remittance.application.port.out;

import com.JHPay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.JHPay.remittance.application.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand  command);
    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}