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
public class StatsResponse {

    private Long totalUsers;
    private Long totalPlats;
    private Long commandesJour;
    private BigDecimal chiffreAffaireJour;
    private BigDecimal chiffreAffaireMois;
    private BigDecimal chiffreAffaireAnnee;
    private BigDecimal ticketMoyenJour;
    private Long commandesEnAttente;
    private Long commandesEnPreparation;
    private Long commandesLivrees;
    private Long commandesAnnulees;
    private Double tauxConversion;
    private BigDecimal panierMoyen;
}
