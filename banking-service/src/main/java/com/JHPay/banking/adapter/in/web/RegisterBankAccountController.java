package com.JHPay.banking.adapter.in.web;

import com.JHPay.banking.application.port.in.RegisterBankAccountCommand;
import com.JHPay.banking.application.port.in.RegisterBankAccountUseCase;
import com.JHPay.banking.domain.RegisteredBankAccount;
import com.JHPay.common.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;
    // Todo
    // 맴버 확인
    // 계좌 조회
    // 동일 계좌 등록 처리
    @PostMapping(path = "/banking/account/register")
    @Operation(summary = "계좌 등록", description = "계좌 등록")
    RegisteredBankAccount registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();

        return registerBankAccountUseCase.registerBankAccount(command);
    }

    @PostMapping(path = "/banking/account/register-eda")
    @Operation(summary = "Event 계좌 등록", description = "Event 계좌 등록")
    void registerBankAccountByEvent(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();

        registerBankAccountUseCase.registerBankAccountByEvent(command);
    }
}
