package com.example.restau.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeRequest {

    @NotNull(message = "L'ID du plat est requis")
    @Positive(message = "L'ID du plat doit être positif")
    private Long platId;

    @NotNull(message = "La quantité est requise")
    @Positive(message = "La quantité doit être supérieure à 0")
    private Integer quantite;
}
