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
public class LigneCommandeResponse {

    private Long id;
    private int quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal sousTotal;
    private Long platId;
    private String platNom;
    private String platImageUrl;
}