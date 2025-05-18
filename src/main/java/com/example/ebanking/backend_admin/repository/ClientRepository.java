package com.example.ebanking.backend_admin.repository;

import com.example.ebanking.backend_admin.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ClientRepository extends JpaRepository<Client, Long> {}
