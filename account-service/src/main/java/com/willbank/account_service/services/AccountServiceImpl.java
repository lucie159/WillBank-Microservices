package com.willbank.account_service.services;

import com.willbank.account_service.clients.ClientRestClient;
import com.willbank.account_service.dtos.AccountRequestDTO;
import com.willbank.account_service.dtos.AccountResponseDTO;
import com.willbank.account_service.entities.Account;
import com.willbank.account_service.mappers.AccountMapper;
import com.willbank.account_service.models.Customer;
import com.willbank.account_service.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRestClient clientRestClient; // Le téléphone

    // Constructeur manuel (Injection des dépendances)
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, ClientRestClient clientRestClient) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.clientRestClient = clientRestClient;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        // 1. Vérification : Est-ce que le client existe vraiment ?
        // Si le client n'existe pas, cette ligne va échouer (Exception 404 du Feign Client)
        Customer customer = clientRestClient.findClientById(accountRequestDTO.clientId());

        // 2. Si OK, on crée le compte
        Account account = accountMapper.toEntity(accountRequestDTO);

        // 3. Sauvegarde
        Account savedAccount = accountRepository.save(account);

        // 4. Retour avec les infos du client
        return accountMapper.toResponse(savedAccount, customer);
    }

    @Override
    public AccountResponseDTO getAccountById(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        // Appel au Service Client pour enrichir la réponse
        Customer customer = clientRestClient.findClientById(account.getClientId());

        return accountMapper.toResponse(account, customer);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        // Pour la liste, c'est plus complexe car appeler le service client
        // pour CHAQUE compte serait trop lourd (N+1 problem).
        // Pour l'instant, on renvoie la liste sans les détails clients (customer = null)
        // ou on le fera plus tard intelligemment.

        return accountRepository.findAll().stream()
                .map(acc -> accountMapper.toResponse(acc, null)) // Customer null pour la liste globale
                .toList();
    }
}