package com.JHPay.banking.adapter.axon.command;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CreateFirmBankingRequestCommand {

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAccount; // only won
}
