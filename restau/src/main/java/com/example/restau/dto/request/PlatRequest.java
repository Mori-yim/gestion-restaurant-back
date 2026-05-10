package com.example.restau.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatRequest {

    @NotBlank(message = "Le nom du plat est requis")
    private String nom;

    private String description;

    @NotNull(message = "Le prix est requis")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    private BigDecimal prix;

    private String imageUrl;

    @NotNull(message = "La catégorie est requise")
    @Positive(message = "L'ID de catégorie doit être positif")
    private Long categorieId;
}