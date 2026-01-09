package com.willbank.account_service.mappers;

import com.willbank.account_service.dtos.AccountRequestDTO;
import com.willbank.account_service.dtos.AccountResponseDTO;
import com.willbank.account_service.entities.Account;
import com.willbank.account_service.enums.AccountStatus;
import com.willbank.account_service.models.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class AccountMapper {

    // 1. DTO vers Entité (Pour la création)
    public Account toEntity(AccountRequestDTO dto) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(dto.balance());
        account.setClientId(dto.clientId());
        account.setType(dto.type());
        account.setCreatedAt(LocalDate.now());
        account.setStatus(AccountStatus.ACTIF);
        return account;
    }

    // 2. Entité vers DTO (Version simple, SANS infos client)
    public AccountResponseDTO toResponse(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getType(),
                account.getStatus(),
                account.getClientId(),
                null // Ici on met null car on n'a pas les infos du client
        );
    }

    // 3. Entité vers DTO (Version complète, AVEC infos client)
    public AccountResponseDTO toResponse(Account account, Customer customer) {
        return new AccountResponseDTO(
                account.getId(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getType(),
                account.getStatus(),
                account.getClientId(),
                customer // <--- Ici on injecte le vrai client
        );
    }

}