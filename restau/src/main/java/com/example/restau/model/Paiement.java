package com.example.restau.model;


import com.example.restau.model.enums.ModePaiement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    private ModePaiement moyen;

    private String statut;

    private LocalDateTime dateHeure;

    private String reference;

    @OneToOne
    @JoinColumn(name = "commande_id", unique = true)
    private Commande commande;

    @PrePersist
    protected void onCreate() {
        dateHeure = LocalDateTime.now();
        if (statut == null) statut = "EN_ATTENTE";
    }
}