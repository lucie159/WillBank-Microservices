package com.willbank.client_service.dtos;

// Un record pour transporter des données
public record ClientRequestDTO(
        String nom,
        String prenom,
        String email,
        String telephone,
        String adresse
) {}