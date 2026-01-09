package com.willbank.client_service.services; // Vérifie bien ton nom de package

import com.willbank.client_service.dtos.ClientRequestDTO;
import com.willbank.client_service.dtos.ClientResponseDTO;

import java.util.List;

public interface ClientService {

    // On prend une requete, on renvoie une reponse
    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);

    List<ClientResponseDTO> getAllClients();

    ClientResponseDTO getClientById(Long id);

    // qui modifier (id)
    ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO);


    void deleteClient(Long id);
}