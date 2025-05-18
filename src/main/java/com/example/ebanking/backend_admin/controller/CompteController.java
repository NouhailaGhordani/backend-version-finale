package com.example.ebanking.backend_admin.controller;

import com.example.ebanking.backend_admin.model.Compte;
import com.example.ebanking.backend_admin.repository.CompteRepository;
import com.example.ebanking.backend_admin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Récupérer tous les comptes
    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        List<Compte> comptes = compteRepository.findAll();
        return ResponseEntity.ok(comptes);
    }

    // Créer un compte (avec vérification du client)
    @PostMapping
    public ResponseEntity<?> createCompte(@RequestBody Compte compte) {
        try {
            // Vérifie que le client existe
            if (compte.getClient() == null || compte.getClient().getId() == null) {
                return ResponseEntity.badRequest().body("L'ID client est obligatoire");
            }

            // Vérifie l'existence du client
            boolean clientExists = clientRepository.existsById(compte.getClient().getId());
            if (!clientExists) {
                return ResponseEntity.badRequest().body("Client non trouvé avec l'ID: " + compte.getClient().getId());
            }

            Compte savedCompte = compteRepository.save(compte);
            return new ResponseEntity<>(savedCompte, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur serveur: " + e.getMessage());
        }
    }

    // Récupérer les comptes d'un client spécifique
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Compte>> getComptesByClient(@PathVariable Long clientId) {
        List<Compte> comptes = compteRepository.findByClientId(clientId);
        return ResponseEntity.ok(comptes);
    }
}