package com.example.ebanking.backend_admin.repository;

import com.example.ebanking.backend_admin.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    // Méthode pour trouver les comptes par ID client
    List<Compte> findByClientId(Long clientId);

    // Méthode optionnelle pour vérifier l'existence par numéro de compte
    boolean existsByNumeroCompte(String numeroCompte);
}