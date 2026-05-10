package com.example.restau.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatResponse {

    private Long id;
    private String nom;
    private String description;
    private BigDecimal prix;
    private String imageUrl;
    private boolean disponible;
    private String categorieNom;
    private Long categorieId;
}
