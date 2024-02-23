package com.JHPay.settlement.adapter.out.service;

import com.JHPay.common.CommonHttpClient;
import com.JHPay.settlement.application.port.out.GetRegisteredBankAccountPort;
import com.JHPay.settlement.application.port.out.RegisteredBankAccountAggregateIdentifier;
import com.JHPay.settlement.application.port.out.RequestFirmBankingPort;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankingServiceAdapter implements GetRegisteredBankAccountPort, RequestFirmBankingPort {

    private final CommonHttpClient commonHttpClient;

    private final String bankingServiceUrl;

    public BankingServiceAdapter(CommonHttpClient commonHttpClient,
                                 @Value("${service.banking.url}") String bankingServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.bankingServiceUrl = bankingServiceUrl;
    }

    @Override
    public RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId){
        String url = String.join("/", bankingServiceUrl, "banking/account", membershipId);
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            System.out.println(jsonResponse);

            // json RegisteredBankAccount
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount.class);

            return new RegisteredBankAccountAggregateIdentifier(
                    registeredBankAccount.getRegisteredBankAccountId()
                    , registeredBankAccount.getAggregateIdentifier()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankName()
                    , registeredBankAccount.getBankAccountNumber()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void requestFirmBanking(String bankName, String bankAccountNumber, int moneyAmount) {
        String url = String.join("/", bankingServiceUrl, "banking/firmbanking/request");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            RequestFirmBankingRequest request =  new RequestFirmBankingRequest(
                    "jhBank",
                    "111-222-333",
                    bankName,
                    bankAccountNumber,
                    moneyAmount
            );

            String jsonResponse = commonHttpClient.sendPostRequest2(url, mapper.writeValueAsString(request)).body();
//            FirmBankingRequest response = mapper.readValue(jsonResponse, new TypeReference<>() {});


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}