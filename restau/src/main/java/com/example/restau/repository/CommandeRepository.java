package com.example.restau.repository;

import com.example.restau.model.Commande;
import com.example.restau.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByClient(User client);

    Page<Commande> findByClient(User client, Pageable pageable);

    @Query("SELECT c FROM Commande c WHERE c.statut = :statut")
    List<Commande> findByStatut(@Param("statut") String statut);

    @Query("SELECT SUM(c.montantTotal) FROM Commande c WHERE DATE(c.dateHeure) = CURRENT_DATE")
    BigDecimal getChiffreAffaireJour();

    @Query("SELECT COUNT(c) FROM Commande c WHERE DATE(c.dateHeure) = CURRENT_DATE")
    Long getNombreCommandesJour();

    @Query("SELECT AVG(c.montantTotal) FROM Commande c WHERE DATE(c.dateHeure) = CURRENT_DATE")
    BigDecimal getTicketMoyenJour();
}
