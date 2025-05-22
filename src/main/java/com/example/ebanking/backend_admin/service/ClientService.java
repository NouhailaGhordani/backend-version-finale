package com.example.ebanking.backend_admin.service;

import com.example.ebanking.backend_admin.dto.ClientGrowthDto;
import com.example.ebanking.backend_admin.model.Client;
import com.example.ebanking.backend_admin.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Méthode pour obtenir la croissance des clients
    public List<ClientGrowthDto> getClientGrowth() {
        // Logique pour calculer la croissance des clients (exemple simple)
        // Cela devrait probablement être plus complexe en fonction de tes besoins réels
        return List.of(new ClientGrowthDto(1, 15), new ClientGrowthDto(2, 30), new ClientGrowthDto(3, 45));
    }

    // Méthode pour obtenir les clients récents
    public List<Client> getRecentClients() {
        return clientRepository.findTop5ByOrderByDateCreationDesc();  // Exemple pour récupérer les 5 derniers clients
    }
}