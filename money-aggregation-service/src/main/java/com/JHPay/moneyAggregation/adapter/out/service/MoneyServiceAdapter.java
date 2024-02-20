package com.JHPay.moneyAggregation.adapter.out.service;

import com.JHPay.common.CommonHttpClient;
import com.JHPay.common.ExternalSystemAdapter;
import com.JHPay.moneyAggregation.application.port.out.GetMoneySumPort;
import com.JHPay.moneyAggregation.application.port.out.MemberMoney;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@ExternalSystemAdapter
@Component
public class MoneyServiceAdapter implements GetMoneySumPort {

    private final CommonHttpClient moneyServiceHttpClient;

    private final String moneyServiceEndpoint;

    public MoneyServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.money.url}") String moneyServiceEndpoint) {
        this.moneyServiceHttpClient = commonHttpClient;
        this.moneyServiceEndpoint = moneyServiceEndpoint;
    }
    @Override
    public List<MemberMoney> getMoneySumByMembershipIds(List<String> membershipIds) {
        String url = String.join("/", moneyServiceEndpoint, "money/member-money");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            FindMemberMoneyRequest request = new FindMemberMoneyRequest(membershipIds);
            String jsonResponse = moneyServiceHttpClient.sendPostRequest(url, mapper.writeValueAsString(request)).get().body();
            return mapper.readValue(jsonResponse, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}