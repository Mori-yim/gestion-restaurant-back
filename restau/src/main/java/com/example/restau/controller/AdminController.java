package com.example.restau.controller;

import com.example.restau.dto.response.MessageResponse;
import com.example.restau.dto.response.StatsResponse;
import com.example.restau.model.Commande;
import com.example.restau.model.User;
import com.example.restau.repository.CommandeRepository;
import com.example.restau.repository.PlatRepository;
import com.example.restau.repository.UserRepository;
import com.example.restau.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//Route principale
@RequestMapping("/api/admin")
//Accessible uniquement par l'admin
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final PlatRepository platRepository;
    private final CommandeRepository commandeRepository;
    private final UserService userService; 

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        StatsResponse stats = StatsResponse.builder()
                .totalUsers(userRepository.count())
                .totalPlats(platRepository.count())
                .commandesJour(commandeRepository.getNombreCommandesJour())
                .chiffreAffaireJour(commandeRepository.getChiffreAffaireJour())
                .ticketMoyenJour(commandeRepository.getTicketMoyenJour())
                .build();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/commandes")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeRepository.findAll());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PatchMapping("/users/{id}/toggle")
    public ResponseEntity<?> toggleUserStatus(@PathVariable Long id) {
        userService.toggleUserStatus(id);
        return ResponseEntity.ok(new MessageResponse("Statut utilisateur modifié"));
    }
}
