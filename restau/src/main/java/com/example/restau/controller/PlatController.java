package com.example.restau.controller;


import com.example.restau.dto.request.PlatRequest;
import com.example.restau.dto.response.MessageResponse;
import com.example.restau.model.Plat;
import com.example.restau.service.PlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/plats")
@RequiredArgsConstructor
public class PlatController {

    private final PlatService platService;

    @GetMapping
    public ResponseEntity<List<Plat>> getAllPlats() {
        return ResponseEntity.ok(platService.getAllPlats());
    }

    @GetMapping("/pagines")
    public ResponseEntity<Page<Plat>> getPlatsPagines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(platService.getPlatsPagines(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plat> getPlatById(@PathVariable Long id) {
        return ResponseEntity.ok(platService.getPlatById(id));
    }

    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<Plat>> getPlatsByCategorie(@PathVariable Long categorieId) {
        return ResponseEntity.ok(platService.getPlatsByCategorie(categorieId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Plat> createPlat(@RequestBody PlatRequest request) {
        return ResponseEntity.ok(platService.createPlat(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Plat> updatePlat(@PathVariable Long id, @RequestBody PlatRequest request) {
        return ResponseEntity.ok(platService.updatePlat(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePlat(@PathVariable Long id) {
        platService.deletePlat(id);
        return ResponseEntity.ok(new MessageResponse("Plat supprimé avec succès"));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Plat> toggleDisponibilite(@PathVariable Long id) {
        return ResponseEntity.ok(platService.toggleDisponibilite(id));
    }
}
