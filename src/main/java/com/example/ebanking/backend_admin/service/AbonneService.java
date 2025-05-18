package com.example.ebanking.backend_admin.service;

import com.example.ebanking.backend_admin.model.Abonne;

import java.util.List;

public interface AbonneService {
    Abonne createAbonne(Abonne abonne);
    List<Abonne> getAllAbonnes();
}
