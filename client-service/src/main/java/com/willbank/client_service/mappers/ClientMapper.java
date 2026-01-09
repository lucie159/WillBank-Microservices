package com.willbank.client_service.mappers;

import com.willbank.client_service.dtos.ClientRequestDTO;
import com.willbank.client_service.dtos.ClientResponseDTO;
import com.willbank.client_service.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // DTO -> Entity
    public Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();
        client.setNom(dto.nom());
        client.setPrenom(dto.prenom());
        client.setEmail(dto.email());
        client.setTelephone(dto.telephone());
        client.setAdresse(dto.adresse());
        return client;
    }

    // Entity -> DTO
    public ClientResponseDTO toResponse(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.getAdresse(),
                client.getStatut(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }
}
