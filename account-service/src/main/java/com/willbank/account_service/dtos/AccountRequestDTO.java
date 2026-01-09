package com.willbank.account_service.dtos;

import com.willbank.account_service.enums.AccountType;


public record AccountRequestDTO(
        Long clientId,
        double balance,
        AccountType type
) {}