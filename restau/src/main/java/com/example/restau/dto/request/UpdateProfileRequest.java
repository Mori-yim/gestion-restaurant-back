package com.example.restau.dto.request;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nomComplet;

    @Pattern(regexp = "^[0-9]{9}$", message = "Le téléphone doit contenir 9 chiffres")
    private String telephone;

    private String adresse;
}
