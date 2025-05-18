package com.example.ebanking.backend_admin.service;

import com.example.ebanking.backend_admin.model.Abonne;
import com.example.ebanking.backend_admin.repository.AbonneRepository;
import com.example.ebanking.backend_admin.service.AbonneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonneServiceImpl implements AbonneService {

    private final AbonneRepository abonneRepository;

    public AbonneServiceImpl(AbonneRepository abonneRepository) {
        this.abonneRepository = abonneRepository;
    }

    @Override
    public Abonne createAbonne(Abonne abonne) {
        return abonneRepository.save(abonne);
    }

    @Override
    public List<Abonne> getAllAbonnes() {
        return abonneRepository.findAll();
    }
}
