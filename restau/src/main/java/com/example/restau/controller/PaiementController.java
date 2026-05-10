package com.example.restau.controller;


import com.example.restau.dto.request.PaiementRequest;
import com.example.restau.model.Paiement;
import com.example.restau.service.PaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    @PostMapping
    public ResponseEntity<Paiement> processPaiement(@RequestBody PaiementRequest request) {
        return ResponseEntity.ok(paiementService.processPaiement(request));
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<Paiement> getPaiementByCommandeId(@PathVariable Long commandeId) {
        return ResponseEntity.ok(paiementService.getPaiementByCommandeId(commandeId));
    }
}