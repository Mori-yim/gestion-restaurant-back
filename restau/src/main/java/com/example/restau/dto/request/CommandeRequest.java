package com.example.restau.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeRequest {

    @NotBlank(message = "Le mode de service est requis")
    @Pattern(regexp = "sur place|emporter|livraison",
            message = "Le mode de service doit être: sur place, emporter ou livraison")
    private String modeService;

    @NotEmpty(message = "La commande doit contenir au moins un plat")
    @Valid
    private List<LigneCommandeRequest> lignes;
}