package com.willbank.account_service.dtos;

import com.willbank.account_service.enums.AccountStatus;
import com.willbank.account_service.enums.AccountType;
import com.willbank.account_service.models.Customer;
import java.time.LocalDate;

public record AccountResponseDTO(
        String id,
        double balance,
        LocalDate createdAt,
        AccountType type,
        AccountStatus status,
        Long clientId,
        Customer customer // le client complet ici !
) {}