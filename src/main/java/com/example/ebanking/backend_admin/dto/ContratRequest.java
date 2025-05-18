package com.example.ebanking.backend_admin.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratRequest {
    private Long clientId;
    private Long compteFacturationId;
    private String offreCommerciale;
    private LocalDate dateCreation;
    private LocalDate dateSouscription;
}
