package com.example.ebanking.backend_admin.repository;

import com.example.ebanking.backend_admin.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, String> {
    // Méthode pour trouver les contrats par ID client
    List<Contrat> findByClientId(Long clientId);

    // Méthode optionnelle pour vérifier l'existence par numéro de contrat
    boolean existsByNumeroContrat(String numeroContrat);
}