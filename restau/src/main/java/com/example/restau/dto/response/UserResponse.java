package com.example.restau.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String nomComplet;
    private String email;
    private String telephone;
    private String adresse;
    private String role;
    private boolean actif;
    private LocalDateTime dateInscription;
}
