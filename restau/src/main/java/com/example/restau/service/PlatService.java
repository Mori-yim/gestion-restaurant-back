package com.example.restau.service;




import com.example.restau.dto.request.PlatRequest;
import com.example.restau.exception.ResourceNotFoundException;
import com.example.restau.model.Categorie;
import com.example.restau.model.Plat;
import com.example.restau.repository.CategorieRepository;
import com.example.restau.repository.PlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatService {

    private final PlatRepository platRepository;
    private final CategorieRepository categorieRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findByDisponibleTrue();
    }

    public Page<Plat> getPlatsPagines(int page, int size) {
        return platRepository.findByDisponibleTrue(PageRequest.of(page, size));
    }

    public Plat getPlatById(Long id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plat non trouvé avec l'id: " + id));
    }

    public List<Plat> getPlatsByCategorie(Long categorieId) {
        if (!categorieRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("Catégorie non trouvée");
        }
        return platRepository.findByCategorieId(categorieId);
    }

    public Plat createPlat(PlatRequest request) {
        Categorie categorie = categorieRepository.findById(request.getCategorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée"));

        Plat plat = Plat.builder()
                .nom(request.getNom())
                .description(request.getDescription())
                .prix(request.getPrix())
                .imageUrl(request.getImageUrl())
                .disponible(true)
                .categorie(categorie)
                .build();

        return platRepository.save(plat);
    }

    public Plat updatePlat(Long id, PlatRequest request) {
        Plat plat = getPlatById(id);

        if (request.getNom() != null) plat.setNom(request.getNom());
        if (request.getDescription() != null) plat.setDescription(request.getDescription());
        if (request.getPrix() != null) plat.setPrix(request.getPrix());
        if (request.getImageUrl() != null) plat.setImageUrl(request.getImageUrl());
        if (request.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(request.getCategorieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée"));
            plat.setCategorie(categorie);
        }

        return platRepository.save(plat);
    }

    public void deletePlat(Long id) {
        Plat plat = getPlatById(id);
        platRepository.delete(plat);
    }

    public Plat toggleDisponibilite(Long id) {
        Plat plat = getPlatById(id);
        plat.setDisponible(!plat.isDisponible());
        return platRepository.save(plat);
    }
}
