package com.example.restau.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorieResponse {

    private Long id;
    private String nom;
    private String description;
    private Long nombrePlats;
}