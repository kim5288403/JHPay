package com.JHPay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmBankingFinishedEvent {
    private String rollbackFirmBankingId;
    private String membershipId;
    private String rollbackFirmBankingAggregateIdentifier;
}
