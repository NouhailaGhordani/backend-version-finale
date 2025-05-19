package com.example.ebanking.backend_admin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "compte")
@Data
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_compte", unique = true, nullable = false)
    private String numeroCompte;

    @PrePersist
    public void generateNumeroCompte() {
        this.numeroCompte = UUID.randomUUID().toString().substring(0, 12); // ou toute autre logique
    }


    @Column(name = "intitule", length = 100)
    private String intitule;

    @Column(name = "devise", length = 3)
    private String devise = "MAD";

    @Column(name = "code_agence", length = 5)
    private String codeAgence;
    @Column(name = "solde")
    private Double solde = 0.0;

    @Column(name = "virement_autorise")
    private boolean virementAutorise = true;

    @Column(name = "demande_service_autorisee")
    private boolean demandeServiceAutorisee = true;

    @Column(name = "remise_ordre_autorisee")
    private boolean remiseOrdreAutorisee = true;

    @Column(name = "consultation_autorisee")
    private boolean consultationAutorisee = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference  // Ne sérialise pas la référence inverse pour éviter boucle
    private Client client;


}


