package com.willbank.client_service.services;

import com.willbank.client_service.dtos.ClientRequestDTO;
import com.willbank.client_service.dtos.ClientResponseDTO;
import com.willbank.client_service.entities.Client;
import com.willbank.client_service.mappers.ClientMapper;
import com.willbank.client_service.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        if (clientRepository.existsByEmail(clientRequestDTO.email())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        Client client = clientMapper.toEntity(clientRequestDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toResponse(savedClient);
    }

    @Override
    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'ID : " + id));
        return clientMapper.toResponse(client);
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        Client clientExist = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'ID : " + id));
        clientExist.setNom(clientRequestDTO.nom());
        clientExist.setPrenom(clientRequestDTO.prenom());
        clientExist.setEmail(clientRequestDTO.email());
        clientExist.setTelephone(clientRequestDTO.telephone());
        clientExist.setAdresse(clientRequestDTO.adresse());

        Client updatedClient = clientRepository.save(clientExist);
        return clientMapper.toResponse(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Client introuvable");
        }
        clientRepository.deleteById(id);
    }
}