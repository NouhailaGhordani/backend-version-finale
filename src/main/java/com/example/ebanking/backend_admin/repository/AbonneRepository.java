package com.example.ebanking.backend_admin.repository;

import com.example.ebanking.backend_admin.model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonneRepository extends JpaRepository<Abonne, Long> {}
