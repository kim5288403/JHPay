package com.JHPay.query.adapter.axon;

import com.JHPay.common.event.RequestFirmBankingFinishedEvent;
import com.JHPay.query.application.port.out.GetMemberAddressInfoPort;
import com.JHPay.query.application.port.out.InsertMoneyIncreaseEventByAddress;
import com.JHPay.query.application.port.out.MemberAddressInfo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class MoneyIncreaseEventHandler {
    @EventHandler
    public void handler(
            RequestFirmBankingFinishedEvent event,
            GetMemberAddressInfoPort getMemberAddressInfoPort,
            InsertMoneyIncreaseEventByAddress insertMoneyIncreaseEventByAddress
    ) {
        System.out.println("Money Increase Event Received: "+ event.toString());


        // 고객의 주소 정보
        MemberAddressInfo memberAddressInfo = getMemberAddressInfoPort.getMemberAddressInfo(event.getMembershipId());

        // Dynamodb Insert!
        String address = memberAddressInfo.getAddress();
        int moneyIncrease = event.getMoneyAmount();

        insertMoneyIncreaseEventByAddress.insertMoneyIncreaseEventByAddress(address, moneyIncrease);
    }
}