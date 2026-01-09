package com.willbank.account_service.controllers;

import com.willbank.account_service.dtos.AccountRequestDTO;
import com.willbank.account_service.dtos.AccountResponseDTO;
import com.willbank.account_service.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    // POST http://localhost:8082/api/accounts
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // GET http://localhost:8082/api/accounts/{id}
    @PostMapping
    public AccountResponseDTO save(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.createAccount(accountRequestDTO);
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccount(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping
    public List<AccountResponseDTO> getAll() {
        return accountService.getAllAccounts();
    }
}