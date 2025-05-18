package com.example.ebanking.backend_admin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contrat")
@Data
public class Contrat {

    @Id
    @Column(name = "numero_contrat", length = 20, unique = true, nullable = false)
    private String numeroContrat;

    @Column(name = "offre_commerciale", nullable = false, length = 100)
    private String offreCommerciale;

    @Column(name = "date_creation", nullable = false)
    @NotNull
    private LocalDate dateCreation;

    @Column(name = "charge_client", length = 100)
    private String chargeClient;  // Nouveau champ

    @Column(name = "statut", nullable = false, length = 20)
    private String statut = "desactive"; // Valeur par défaut

    @Column(name = "date_souscription")
    private LocalDate dateSouscription;

    @Column(name = "code_agence", nullable = false, length = 5)
    private String codeAgence = "AG001";  // Valeur par défaut

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_facturation_id", nullable = false)
    @NotNull
    private Compte compteFacturation;

    @PrePersist
    public void generateNumeroContrat() {
        if (this.numeroContrat == null || this.numeroContrat.trim().isEmpty()) {
            this.numeroContrat = "CONT-" +
                    UUID.randomUUID().toString()
                            .replace("-", "")
                            .substring(0, 8)
                            .toUpperCase();
        }
    }
}