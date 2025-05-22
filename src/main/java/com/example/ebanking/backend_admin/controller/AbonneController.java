package com.example.ebanking.backend_admin.controller;

import com.example.ebanking.backend_admin.model.Abonne;
import com.example.ebanking.backend_admin.repository.AbonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/abonnes")
@CrossOrigin(origins = "http://localhost:3000") // Autoriser frontend React
public class AbonneController {

    @Autowired
    private AbonneRepository abonneRepository;

    // Créer un nouvel abonné
    @PostMapping
    public ResponseEntity<Abonne> createAbonne(@RequestBody Abonne abonne) {
        try {
            Abonne savedAbonne = abonneRepository.save(abonne);
            return ResponseEntity.created(URI.create("/api/abonnes/" + savedAbonne.getId())).body(savedAbonne);
        } catch (Exception e) {
            e.printStackTrace();  // Affiche l'erreur complète dans la console
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la création de l'abonné", e);
        }
    }

    // Récupérer tous les abonnés
    @GetMapping
    public List<Abonne> getAllAbonnes() {
        return abonneRepository.findAll();
    }

    // Récupérer un abonné par ID
    @GetMapping("/{id}")
    public ResponseEntity<Abonne> getAbonneById(@PathVariable Long id) {
        Optional<Abonne> abonne = abonneRepository.findById(id);
        return abonne.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonné non trouvé"));
    }

    // Mettre à jour un abonné
    @PutMapping("/{id}")
    public ResponseEntity<Abonne> updateAbonne(@PathVariable Long id, @RequestBody Abonne abonneDetails) {
        try {
            Abonne abonne = abonneRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonné non trouvé"));

            abonne.setNom(abonneDetails.getNom());
            abonne.setPrenom(abonneDetails.getPrenom());
            abonne.setSexe(abonneDetails.getSexe());
            abonne.setTelephone(abonneDetails.getTelephone());
            abonne.setLangue(abonneDetails.getLangue());
            abonne.setEmail(abonneDetails.getEmail());
            abonne.setTypePieceIdentite(abonneDetails.getTypePieceIdentite());
            abonne.setNumeroPieceIdentite(abonneDetails.getNumeroPieceIdentite());
            abonne.setCodeAgence(abonneDetails.getCodeAgence());
            abonne.setStatut(abonneDetails.getStatut());

            Abonne updatedAbonne = abonneRepository.save(abonne);
            return new ResponseEntity<>(updatedAbonne, HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de l'abonné", e);
        }
    }

    // Supprimer un abonné
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonne(@PathVariable Long id) {
        Optional<Abonne> abonneOptional = abonneRepository.findById(id);
        if (abonneOptional.isPresent()) {
            abonneRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Abonné non trouvé");
        }
    }
    // AbonneController.java
    @GetMapping("/count")
    public ResponseEntity<Long> getAbonneCount() {
        long abonneCount = abonneRepository.count();
        return ResponseEntity.ok(abonneCount);
    }

}
