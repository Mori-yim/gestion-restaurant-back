package com.example.restau.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementResponse {

    private Long id;
    private BigDecimal montant;
    private String moyen;
    private String statut;
    private LocalDateTime dateHeure;
    private String reference;
    private Long commandeId;
}
