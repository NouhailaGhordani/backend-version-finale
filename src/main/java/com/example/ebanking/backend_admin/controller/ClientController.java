package com.example.ebanking.backend_admin.controller;

import com.example.ebanking.backend_admin.model.Client;
import com.example.ebanking.backend_admin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
// HA HUWA CLIENT
@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:3000")  // Autorise React sur ce domaine
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Récupérer la liste des clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    // Créer un nouveau client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client savedClient = clientRepository.save(client);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        } catch (Exception e) {
            // Gestion d’erreur simplifiée, tu peux améliorer
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un client par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        System.out.println("Suppression du client avec l'ID: " + id); // ← log temporaire
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
        }
    }

    // Ajoute cette méthode dans ton ClientController.java

    // Mettre à jour un client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            // Met à jour les champs (à adapter selon ton entity Client)
            client.setIntituleClient(clientDetails.getIntituleClient());
            client.setSegmentClient(clientDetails.getSegmentClient());
            client.setNumeroClient(clientDetails.getNumeroClient());
            client.setEmail(clientDetails.getEmail());
            client.setTelephone(clientDetails.getTelephone());
            client.setDateCreation(clientDetails.getDateCreation());
            // Sauvegarde
            Client updatedClient = clientRepository.save(client);
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
