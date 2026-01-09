package com.willbank.account_service.services;

import com.willbank.account_service.dtos.AccountRequestDTO;
import com.willbank.account_service.dtos.AccountResponseDTO;
import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO getAccountById(String id);
    List<AccountResponseDTO> getAllAccounts();
}