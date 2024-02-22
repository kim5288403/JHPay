package com.JHPay.query.application.service;


import com.JHPay.common.UseCase;
import com.JHPay.query.adapter.axon.QueryMoneySumByAddress;
import com.JHPay.query.application.port.in.QueryMoneySumByRegionQuery;
import com.JHPay.query.application.port.in.QueryMoneySumByRegionUseCase;
import com.JHPay.query.domain.MoneySumByRegion;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;

import javax.transaction.Transactional;
import java.util.concurrent.ExecutionException;

@UseCase
@RequiredArgsConstructor
@Transactional
public class QueryMoneySumByRegionService implements QueryMoneySumByRegionUseCase {
    private final QueryGateway queryGateway;
    @Override
    public MoneySumByRegion queryMoneySumByRegion(QueryMoneySumByRegionQuery query) {
        try {
            return queryGateway.query(new QueryMoneySumByAddress(query.getAddress())
                    , MoneySumByRegion.class).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}