package com.willbank.account_service.clients;

import com.willbank.account_service.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

// nom du service
@FeignClient(name = "client-service")
public interface ClientRestClient {

    // On copie la signature de la méthode GET du ClientController
    @GetMapping("/api/clients/{id}")
    Customer findClientById(@PathVariable("id") Long id);

    // On peut aussi récupérer tous les clients
    @GetMapping("/api/clients")
    List<Customer> allClients();
}