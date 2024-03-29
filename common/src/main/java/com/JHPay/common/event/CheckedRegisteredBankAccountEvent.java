package com.JHPay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedRegisteredBankAccountEvent {

    private String rechargingRequestId;

    private String checkRegisteredBankAccountId;

    private String membershipId;

    private boolean isChecked;

    private int amount;

    private String firmBankingRequestAggregateIdentifier;

    private String toBankName;

    private String toBankAccountNumber;
    @Override
    public String toString() {
        return "CheckedRegisteredBankAccountEvent{" +
                "rechargingRequestId='" + rechargingRequestId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", isChecked=" + isChecked +
                ", amount=" + amount +
                ", firmBankingRequestAggregateIdentifier='" + firmBankingRequestAggregateIdentifier + '\'' +
                '}';
    }
}