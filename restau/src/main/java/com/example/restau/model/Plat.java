package com.example.restau.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "plat")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    private String imageUrl;

    private boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
}
