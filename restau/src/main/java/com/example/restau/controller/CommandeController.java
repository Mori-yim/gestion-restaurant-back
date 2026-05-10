package com.example.restau.controller;

import com.example.restau.dto.request.CommandeRequest;
import com.example.restau.model.Commande;
import com.example.restau.model.enums.StatutCommande;
import com.example.restau.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody CommandeRequest request) {
        return ResponseEntity.ok(commandeService.createCommande(request));
    }

    @GetMapping("/mes-commandes")
    public ResponseEntity<List<Commande>> getMesCommandes() {
        return ResponseEntity.ok(commandeService.getMesCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PostMapping("/{id}/annuler")
    public ResponseEntity<Commande> annulerCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.annulerCommande(id));
    }

    @PutMapping("/{id}/statut")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public ResponseEntity<Commande> updateStatut(@PathVariable Long id, @RequestParam StatutCommande statut) {
        return ResponseEntity.ok(commandeService.updateStatut(id, statut));
    }
}

