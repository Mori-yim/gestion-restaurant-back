package com.example.restau.service;


import com.example.restau.dto.request.PaiementRequest;
import com.example.restau.exception.BadRequestException;
import com.example.restau.exception.ResourceNotFoundException;
import com.example.restau.model.Commande;
import com.example.restau.model.Paiement;
import com.example.restau.model.enums.ModePaiement;
import com.example.restau.model.enums.StatutCommande;
import com.example.restau.repository.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final CommandeService commandeService;

    @Transactional
    public Paiement processPaiement(PaiementRequest request) {
        Commande commande = commandeService.getCommandeById(request.getCommandeId());

        // Vérifier que la commande est en attente
        if (commande.getStatut() != StatutCommande.EN_ATTENTE) {
            throw new BadRequestException("Cette commande ne peut plus être payée");
        }

        // Vérifier qu'il n'y a pas déjà un paiement
        if (paiementRepository.existsByCommandeId(commande.getId())) {
            throw new BadRequestException("Cette commande a déjà été payée");
        }

        // Créer le paiement
        Paiement paiement = Paiement.builder()
                .montant(commande.getMontantTotal())
                .moyen(ModePaiement.valueOf(request.getMoyen()))
                .statut("VALIDÉ")
                .reference(genererReference())
                .commande(commande)
                .dateHeure(LocalDateTime.now())
                .build();

        // Changer le statut de la commande
        commandeService.updateStatut(commande.getId(), StatutCommande.EN_PREPARATION);

        return paiementRepository.save(paiement);
    }

    public Paiement getPaiementByCommandeId(Long commandeId) {
        return paiementRepository.findByCommandeId(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé pour cette commande"));
    }

    private String genererReference() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
