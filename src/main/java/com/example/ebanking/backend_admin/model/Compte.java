package com.example.ebanking.backend_admin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "compte")
@Data
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_compte", unique = true, nullable = false)
    private String numeroCompte;

    @Column(name = "intitule", length = 100)
    private String intitule;

    @Column(name = "devise", length = 3)
    private String devise = "MAD";

    @Column(name = "code_agence", length = 5)
    private String codeAgence;

    @Column(name = "code_banque", length = 5)
    private String codeBanque;

    @Column(name = "solde")
    private Double solde = 0.0;

    @Column(name = "rib", length = 30)
    private String rib;

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
    @JsonBackReference
    private Client client;

    @PrePersist
    public void generateNumeroCompteEtRib() {
        if (this.numeroCompte == null || this.numeroCompte.isEmpty()) {
            String prefix = "2111";
            String datePart = java.time.LocalDate.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("yyMM"));
            String randomPart = String.format("%08d", (int)(Math.random() * 1_0000_0000));
            this.numeroCompte = prefix + datePart + randomPart;
        }

        // Valeurs par défaut si null
        if (this.codeBanque == null) this.codeBanque = "190";
        if (this.codeAgence == null) this.codeAgence = "780";

        String ribNumeric = codeBanque + codeAgence + numeroCompte;
        int cleRib = 97 - modulo97(ribNumeric);
        this.rib = codeBanque + codeAgence + numeroCompte + String.format("%02d", cleRib);
    }

    private int modulo97(String number) {
        StringBuilder numeric = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                numeric.append(c);
            }
        }

        int chunkSize = 9;
        long total = 0;
        int i = 0;

        while (i < numeric.length()) {
            int end = Math.min(i + chunkSize, numeric.length());
            String chunk = (total == 0 ? "" : Long.toString(total)) + numeric.substring(i, end);
            total = Long.parseLong(chunk) % 97;
            i = end;
        }

        return (int) total;
    }
}
