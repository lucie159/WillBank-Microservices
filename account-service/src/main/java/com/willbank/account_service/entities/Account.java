package com.willbank.account_service.entities;

import com.willbank.account_service.enums.AccountStatus;
import com.willbank.account_service.enums.AccountType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private String id; // (String aléatoire) au lieu d'un nombre

    private double balance;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private Long clientId; // Lien vers le client (mais sans relation @ManyToOne car microservice séparé)


    public Account() {
    }


    public Account(String id, double balance, LocalDate createdAt, AccountType type, AccountStatus status, Long clientId) {
        this.id = id;
        this.balance = balance;
        this.createdAt = createdAt;
        this.type = type;
        this.status = status;
        this.clientId = clientId;
    }



    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public AccountType getType() { return type; }
    public void setType(AccountType type) { this.type = type; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
}