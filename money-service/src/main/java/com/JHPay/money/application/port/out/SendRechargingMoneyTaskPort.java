package com.JHPay.money.application.port.out;

import com.JHPay.common.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {

    void sendRechargingMoneyTaskPort(
            RechargingMoneyTask task
    );
}
