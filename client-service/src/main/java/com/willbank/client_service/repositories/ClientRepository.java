package com.willbank.client_service.repositories;

import com.willbank.client_service.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
   // Spring Data génère tout

    boolean existsByEmail(String email);
}