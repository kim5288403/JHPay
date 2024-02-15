package com.JHPay.banking.adapter.axon.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFirmBankingRequestCommand {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private int status;
}
