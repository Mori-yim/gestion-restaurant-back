package com.example.restau.service;


import com.example.restau.dto.request.CommandeRequest;
import com.example.restau.dto.request.LigneCommandeRequest;
import com.example.restau.exception.BadRequestException;
import com.example.restau.exception.ResourceNotFoundException;
import com.example.restau.model.Commande;
import com.example.restau.model.LigneCommande;
import com.example.restau.model.Plat;
import com.example.restau.model.User;
import com.example.restau.model.enums.StatutCommande;
import com.example.restau.repository.CommandeRepository;
import com.example.restau.repository.LigneCommandeRepository;
import com.example.restau.repository.PlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final PlatRepository platRepository;
    private final UserService userService;

    @Transactional
    public Commande createCommande(CommandeRequest request) {
        User client = userService.getCurrentUser();

        // Vérifier le montant minimum
        BigDecimal total = BigDecimal.ZERO;
        List<LigneCommande> lignes = new ArrayList<>();

        for (LigneCommandeRequest ligneRequest : request.getLignes()) {
            Plat plat = platRepository.findById(ligneRequest.getPlatId())
                    .orElseThrow(() -> new ResourceNotFoundException("Plat non trouvé"));

            if (!plat.isDisponible()) {
                throw new BadRequestException("Le plat " + plat.getNom() + " n'est pas disponible");
            }

            BigDecimal ligneTotal = plat.getPrix().multiply(BigDecimal.valueOf(ligneRequest.getQuantite()));
            total = total.add(ligneTotal);

            LigneCommande ligne = LigneCommande.builder()
                    .plat(plat)
                    .quantite(ligneRequest.getQuantite())
                    .prixUnitaire(plat.getPrix())
                    .build();
            lignes.add(ligne);
        }

        // Vérifier montant minimum
        if (total.compareTo(new BigDecimal("500")) < 0) {
            throw new BadRequestException("Le montant minimum de commande est de 500 FCFA");
        }

        // Créer la commande
        Commande commande = Commande.builder()
                .client(client)
                .modeService(request.getModeService())
                .montantTotal(total)
                .statut(StatutCommande.EN_ATTENTE)
                .lignes(new ArrayList<>())
                .build();

        commande = commandeRepository.save(commande);

        // Associer les lignes à la commande
        for (LigneCommande ligne : lignes) {
            ligne.setCommande(commande);
            ligneCommandeRepository.save(ligne);
            commande.getLignes().add(ligne);
        }

        return commande;
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée"));
    }

    public List<Commande> getMesCommandes() {
        User client = userService.getCurrentUser();
        return commandeRepository.findByClient(client);
    }

    @Transactional
    public Commande updateStatut(Long id, StatutCommande statut) {
        Commande commande = getCommandeById(id);

        if (commande.getStatut() == StatutCommande.ANNULEE && statut != StatutCommande.ANNULEE) {
            throw new BadRequestException("Impossible de modifier une commande annulée");
        }

        commande.setStatut(statut);
        return commandeRepository.save(commande);
    }

    @Transactional
    public Commande annulerCommande(Long id) {
        Commande commande = getCommandeById(id);

        if (commande.getStatut() != StatutCommande.EN_ATTENTE) {
            throw new BadRequestException("Impossible d'annuler une commande déjà en préparation");
        }

        commande.setStatut(StatutCommande.ANNULEE);
        return commandeRepository.save(commande);
    }
}
