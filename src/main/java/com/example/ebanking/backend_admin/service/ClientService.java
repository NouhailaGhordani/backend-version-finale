package com.example.ebanking.backend_admin.service;

import com.example.ebanking.backend_admin.model.Client;
import com.example.ebanking.backend_admin.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}