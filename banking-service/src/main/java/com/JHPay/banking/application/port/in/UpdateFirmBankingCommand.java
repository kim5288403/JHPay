package com.JHPay.banking.application.port.in;

import com.JHPay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmBankingCommand extends SelfValidating<UpdateFirmBankingCommand> {

    @NotNull
    private String firmBankingAggregateIdentifier;

    @NotNull
    private int status;

    public UpdateFirmBankingCommand(
            String firmBankingAggregateIdentifier,
            int status
    ) {
        this.firmBankingAggregateIdentifier = firmBankingAggregateIdentifier;
        this.status = status;

        this.validateSelf();
    }
}
