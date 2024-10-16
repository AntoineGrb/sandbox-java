package org.springframework.boot.java_sandbox.application.mapper;

import org.springframework.boot.java_sandbox.application.dto.in.MouvementDTORequest;
import org.springframework.boot.java_sandbox.domain.model.Mouvement;
import org.springframework.stereotype.Component;

@Component
public class MouvementDTOMapper {

    public MouvementDTORequest fromMouvementToMouvementDTORequest(Mouvement mouvement) {
        return MouvementDTORequest.builder()
                .nomProduit(mouvement.getNomProduit())
                .date(mouvement.getDate())
                .type(mouvement.getType())
                .montant(mouvement.getMontant())
                .statut(mouvement.getStatut())
                .build();
    }

    public Mouvement fromMouvementDTORequestToMouvement(MouvementDTORequest mouvementDTO) {
        return Mouvement.builder()
                .nomProduit(mouvementDTO.getNomProduit())
                .date(mouvementDTO.getDate())
                .type(mouvementDTO.getType())
                .montant(mouvementDTO.getMontant())
                .statut(mouvementDTO.getStatut())
                .build();
    }
}
