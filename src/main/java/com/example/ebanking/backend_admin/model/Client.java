package com.example.ebanking.backend_admin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_client", unique = true, length = 20)
    private String numeroClient;

    @Column(name = "intitule_client", length = 100)
    private String intituleClient;

    @Column(name = "segment_client", length = 50)
    private String segmentClient;

    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "charge_client", length = 100)
    private String chargeClient;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Sérialise cette relation
    private List<Compte> comptes = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contrat> contrats = new ArrayList<>();



    // Méthode utilitaire pour gérer la relation bidirectionnelle
    public void addCompte(Compte compte) {
        comptes.add(compte);
        compte.setClient(this);
    }

    public void removeCompte(Compte compte) {
        comptes.remove(compte);
        compte.setClient(null);
    }

    // Cette méthode sera appelée automatiquement avant insertion en base
    @PrePersist
    public void generateNumeroClient() {
        if (this.numeroClient == null || this.numeroClient.isEmpty()) {
            String prefix = "CLT";
            String year = String.valueOf(Year.now().getValue());
            int randomNum = (int)(Math.random() * 900000) + 100000;  // nombre à 6 chiffres
            this.numeroClient = prefix + year + randomNum;
        }
        if (this.dateCreation == null) {
            this.dateCreation = LocalDate.now(); // Ajoute cette ligne pour remplir automatiquement
        }
    }
}
