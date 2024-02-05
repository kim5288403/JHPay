package com.JHPay.banking.adapter.out.external.bank;

import com.JHPay.banking.application.port.out.RequestBankAccountInfoPort;
import com.JHPay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.JHPay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {


    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        // 실제로 외부 은행에 http call 을 통해서
        // 실제 은행 계좌 정보를 가져와서
        // 실제 은행 계좌를 -> bankAccout로 파싱

        return new BankAccount(request.getBankAccountNumber(), request.getBankName(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest externalFirmBankingRequest) {
        // 실제로 외부 은행에 http call 을 통해서
        // 펌뱅킹 요청을 하고
        // 외부은행 결과를 -> FirmBankingResult 파싱

        return new FirmBankingResult(0);
    }
}
