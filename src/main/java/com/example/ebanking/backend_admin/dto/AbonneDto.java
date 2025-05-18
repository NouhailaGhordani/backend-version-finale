package com.example.ebanking.backend_admin.dto;
import com.example.ebanking.backend_admin.model.TypePieceIdentite;

public class AbonneDto {
    private String nom;
    private String prenom;
    private TypePieceIdentite typePieceIdentite;
    private String numeroPieceIdentite;
    private String telephone;
    private String email;
    private String langue;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public TypePieceIdentite getTypePieceIdentite() {
        return typePieceIdentite;
    }

    public void setTypePieceIdentite(TypePieceIdentite typePieceIdentite) {
        this.typePieceIdentite = typePieceIdentite;
    }

    public String getNumeroPieceIdentite() {
        return numeroPieceIdentite;
    }

    public void setNumeroPieceIdentite(String numeroPieceIdentite) {
        this.numeroPieceIdentite = numeroPieceIdentite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}