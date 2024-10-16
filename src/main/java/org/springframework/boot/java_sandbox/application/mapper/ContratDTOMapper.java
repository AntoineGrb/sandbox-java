package org.springframework.boot.java_sandbox.application.mapper;

import org.springframework.boot.java_sandbox.application.dto.in.ContratDTORequest;
import org.springframework.boot.java_sandbox.application.dto.out.*;
import org.springframework.boot.java_sandbox.domain.model.Contrat;
import org.springframework.boot.java_sandbox.domain.model.ContratPerp;
import org.springframework.stereotype.Component;

import java.util.List;

/*
Ce mapper permet de convertir un objet Contrat en ContratDTO et vice versa.
On utilise pour cela des builders.
 */

@Component
public class ContratDTOMapper {

    //* Les mappers pour les contrats à retourner dans les réponses
    // Mapper le modèle Contrat en ContratDTO
    public ContratDTO fromContratToContratDTO(Contrat contrat) {
        ContratDTO.ContratDTOBuilder contratDTOBuilder = ContratDTO.builder()
                .numContrat(contrat.getNumContrat())
                .codeProduit(contrat.getCodeProduit())
                .idAssure(contrat.getIdAssure())
                .statut(contrat.getStatut())
                .nom(contrat.getNom())
                .prenom(contrat.getPrenom())
                .codePostal(contrat.getCodePostal())
                .ville(contrat.getVille())
                .pays(contrat.getPays());

        if (contrat.getEpargne() != null) {
            EpargneDTO epargneDTO = EpargneDTO.builder()
                    .nbPart(contrat.getEpargne().getNbPart())
                    .epargneAcquise(contrat.getEpargne().getEpargneAcquise())
                    .build();

            contratDTOBuilder.epargne(epargneDTO);
        }

        if (contrat.getMouvements() != null) {
            List<MouvementDTO> mouvementDTOS = contrat.getMouvements().stream().map(mouvement ->
                    MouvementDTO.builder()
                            .numMouv(mouvement.getNumMouv())
                            .nomProduit(mouvement.getNomProduit())
                            .date(mouvement.getDate())
                            .type(mouvement.getType())
                            .montant(mouvement.getMontant())
                            .statut(mouvement.getStatut())
                            .build()
            ).toList();

            contratDTOBuilder.mouvements(mouvementDTOS);
        }

        return contratDTOBuilder.build();
    }

    // Mapper le modèle ContratPerp en ContratPerpDTO
    public ContratPerpDTO fromContratPerpToContratDTO(ContratPerp contratPerp) {
        ContratPerpDTO.ContratPerpDTOBuilder contratPerpDTOBuilder = ContratPerpDTO.builder()
                .numContrat(contratPerp.getNumContrat())
                .codeProduit(contratPerp.getCodeProduit())
                .idAssure(contratPerp.getIdAssure())
                .statut(contratPerp.getStatut())
                .nom(contratPerp.getNom())
                .prenom(contratPerp.getPrenom())
                .codePostal(contratPerp.getCodePostal())
                .ville(contratPerp.getVille())
                .pays(contratPerp.getPays())
                .isCnil(contratPerp.getIsCnil()); // Nouveau champ spécifique à ContratPerp

        // Ajout de l'épargne PERP si présente
        if (contratPerp.getEpargnePerp() != null) {
            EpargnePerpDTO epargnePerpDTO = EpargnePerpDTO.builder()
                    .nbPart(contratPerp.getEpargnePerp().getNbPart())
                    .epargneAcquise(contratPerp.getEpargnePerp().getEpargneAcquise())
                    .build();

            contratPerpDTOBuilder.epargnePerp(epargnePerpDTO);
        }

        // Ajout des mouvements PERP s'ils sont présents
        if (contratPerp.getMouvementsPerp() != null) {
            List<MouvementPerpDTO> mouvementPerpDTOS = contratPerp.getMouvementsPerp().stream().map(mouvement ->
                    MouvementPerpDTO.builder()
                            .numMouv(mouvement.getNumMouv())
                            .nomProduit(mouvement.getNomProduit())
                            .date(mouvement.getDate())
                            .type(mouvement.getType())
                            .montant(mouvement.getMontant())
                            .statut(mouvement.getStatut())
                            .build()
            ).toList();

            contratPerpDTOBuilder.mouvementsPerp(mouvementPerpDTOS);
        }

        return contratPerpDTOBuilder.build();
    }

    //! Mapper modèle ContratDTO en Contrat
    //! Mapper modèle ContratPerpDTO en ContratPerp

    //* Les mappers pour les contrats reçus par les requêtes
    //Mapper le modèle Contrat en ContratDTORequest
    public ContratDTORequest fromContratToContratDTORequest(Contrat contrat) {
        return ContratDTORequest.builder()
                .nom(contrat.getNom())
                .prenom(contrat.getPrenom())
                .statut(contrat.getStatut())
                .codePostal(contrat.getCodePostal())
                .ville(contrat.getVille())
                .pays(contrat.getPays())
                .build();
    }

    //Mapper le modèle ContratDTORequest en Contrat
    public Contrat fromContratDTORequestToContrat(ContratDTORequest contratDTORequest) {
        return Contrat.builder()
                .nom(contratDTORequest.getNom())
                .prenom(contratDTORequest.getPrenom())
                .statut(contratDTORequest.getStatut())
                .codePostal(contratDTORequest.getCodePostal())
                .ville(contratDTORequest.getVille())
                .pays(contratDTORequest.getPays())
                .build();
    }

}
