package com.willbank.account_service.repositories;

import com.willbank.account_service.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {



    List<Account> findByClientId(Long clientId);
}