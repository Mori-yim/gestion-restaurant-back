package com.example.restau.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeResponse {

    private Long id;
    private LocalDateTime dateHeure;
    private String statut;
    private String modeService;
    private BigDecimal montantTotal;
    private Long clientId;
    private String clientNom;
    private List<LigneCommandeResponse> lignes;
    private PaiementResponse paiement;
}
