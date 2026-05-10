package com.example.restau.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementRequest {

    @NotNull(message = "L'ID de la commande est requis")
    @Positive(message = "L'ID de la commande doit être positif")
    private Long commandeId;

    @NotNull(message = "Le moyen de paiement est requis")
    @Pattern(regexp = "CARTE_BANCAIRE|MTN_MOMO|ORANGE_MONEY",
            message = "Le moyen de paiement doit être: CARTE_BANCAIRE, MTN_MOMO ou ORANGE_MONEY")
    private String moyen;

    private String telephone; // Pour Mobile Money

    private String reference; // Référence de transaction externe
}