package com.example.ebanking.backend_admin.controller;

import com.example.ebanking.backend_admin.model.Client;
import com.example.ebanking.backend_admin.model.Compte;
import com.example.ebanking.backend_admin.model.Contrat;
import com.example.ebanking.backend_admin.repository.ContratRepository;
import com.example.ebanking.backend_admin.repository.ClientRepository;
import com.example.ebanking.backend_admin.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    // Récupérer tous les contrats
    @GetMapping
    public ResponseEntity<List<Contrat>> getAllContrats() {
        List<Contrat> contrats = contratRepository.findAll();
        return ResponseEntity.ok(contrats);
    }

    // Créer un contrat (avec vérifications des relations)
    @PostMapping
    public ResponseEntity<?> createContrat(@RequestBody Contrat contrat) {
        if (contrat.getStatut() == null) {
            contrat.setStatut("desactive"); // Force la valeur par défaut
        }
        try {
            // Validation des champs obligatoires
            if (contrat.getClient() == null || contrat.getClient().getId() == null) {
                return ResponseEntity.badRequest().body("L'ID client est obligatoire");
            }
            if (contrat.getCompteFacturation() == null || contrat.getCompteFacturation().getId() == null) {
                return ResponseEntity.badRequest().body("L'ID compte est obligatoire");
            }

            // Chargement des entités complètes
            Client client = clientRepository.findById(contrat.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé"));

            Compte compte = compteRepository.findById(contrat.getCompteFacturation().getId())
                    .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

            // Création d'un nouveau contrat avec les relations
            Contrat newContrat = new Contrat();
            newContrat.setOffreCommerciale(contrat.getOffreCommerciale());
            newContrat.setDateCreation(contrat.getDateCreation());
            newContrat.setDateSouscription(contrat.getDateSouscription());
            newContrat.setCodeAgence(contrat.getCodeAgence());
            newContrat.setClient(client);
            newContrat.setCompteFacturation(compte);

            Contrat savedContrat = contratRepository.save(newContrat);
            return new ResponseEntity<>(savedContrat, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erreur serveur: " + e.getMessage());
        }
    }

    // Récupérer les contrats d'un client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Contrat>> getContratsByClient(@PathVariable Long clientId) {
        List<Contrat> contrats = contratRepository.findByClientId(clientId);
        return ResponseEntity.ok(contrats);
    }
}