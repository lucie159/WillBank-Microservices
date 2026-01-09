package com.willbank.client_service.controllers;

import com.willbank.client_service.dtos.ClientRequestDTO;
import com.willbank.client_service.dtos.ClientResponseDTO;
import com.willbank.client_service.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dit à Spring que c'est une API REST (répond en JSON)
@RequestMapping("/api/clients") // L'adresse de base pour tout ce fichier
public class ClientController {

    private final ClientService clientService;

    // --- REMPLACEMENT DE LOMBOK ---
    // Injection du Service via le constructeur
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // 1. Lire tous les clients
    // GET http://localhost:8081/api/clients
    @GetMapping
    public List<ClientResponseDTO> getAll() {
        return clientService.getAllClients();
    }

    // 2. Lire un client par son ID
    // GET http://localhost:8081/api/clients/1
    @GetMapping("/{id}")
    public ClientResponseDTO getById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    // 3. Créer un nouveau client
    // POST http://localhost:8081/api/clients
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Renvoie le code 201 (Created) au lieu de 200
    public ClientResponseDTO save(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    // 4. Mettre à jour un client
    // PUT http://localhost:8081/api/clients/1
    @PutMapping("/{id}")
    public ClientResponseDTO update(@PathVariable Long id, @RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.updateClient(id, clientRequestDTO);
    }

    // 5. Supprimer un client
    // DELETE http://localhost:8081/api/clients/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Renvoie 204 (Succès mais pas de contenu)
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}