package com.example.restau.repository;


import com.example.restau.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Optional<Paiement> findByCommandeId(Long commandeId);
    boolean existsByCommandeId(Long commandeId);
}
