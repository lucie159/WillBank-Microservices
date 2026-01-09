package com.willbank.client_service.dtos;

import java.time.LocalDateTime;

public record ClientResponseDTO(
        Long id,
        String nom,
        String prenom,
        String email,
        String telephone,
        String adresse,
        String statut,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}