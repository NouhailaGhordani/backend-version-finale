package com.example.ebanking.backend_admin.dto;

import lombok.Data;

@Data
public class CompteRequest {
    private String numeroCompte;
    private String intitule;
    private String devise;
    private boolean virementAutorise;
    private boolean demandeServiceAutorisee;
    private boolean remiseOrdreAutorisee;
    private boolean consultationAutorisee;
    private Long clientId;
}
