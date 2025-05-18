package com.example.ebanking.backend_admin.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Abonne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroAbonne;

    private String nom;
    private String prenom;
    private String sexe;
    private String telephone;
    private String langue;
    private String email;
    private String typePieceIdentite;
    private String numeroPieceIdentite;
    private String codeAgence;
    private String statut;
    private String dateCreation;

    public Abonne() {}

    @PrePersist
    public void prePersist() {
        this.numeroAbonne = generateNumeroAbonne();
        this.dateCreation = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String generateNumeroAbonne() {
        return "ABN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Getters et Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroAbonne() { return numeroAbonne; }
    public void setNumeroAbonne(String numeroAbonne) { this.numeroAbonne = numeroAbonne; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTypePieceIdentite() { return typePieceIdentite; }
    public void setTypePieceIdentite(String typePieceIdentite) { this.typePieceIdentite = typePieceIdentite; }

    public String getNumeroPieceIdentite() { return numeroPieceIdentite; }
    public void setNumeroPieceIdentite(String numeroPieceIdentite) { this.numeroPieceIdentite = numeroPieceIdentite; }

    public String getCodeAgence() { return codeAgence; }
    public void setCodeAgence(String codeAgence) { this.codeAgence = codeAgence; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }
}
