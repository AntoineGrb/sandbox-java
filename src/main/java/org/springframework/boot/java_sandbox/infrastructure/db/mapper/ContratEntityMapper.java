package org.springframework.boot.java_sandbox.infrastructure.db.mapper;

import org.springframework.boot.java_sandbox.domain.model.*;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.perp.ContratPerpEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.perp.EpargnePerpEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.perp.MouvementPerpEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite.ContratEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite.EpargneEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite.MouvementEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
    Le mapper ContratEntityMapper permet de convertir les données de l'entité ContratEntity en modèle Contrat
    Il est utilisé dans l'adapter ContratRepositoryAdapter pour convertir les données du repository en modèle et vice-versa
 */

@Component
public class ContratEntityMapper {

    //Pour les contrats Retraite
    public Contrat fromContratEntityToContrat(ContratEntity entity) {
        //On crée une instance de notre ContratBuilder (créé par Lombok) et on lui passe les valeurs de l'entity
        Contrat.ContratBuilder contratBuilder = Contrat.builder().numContrat(entity.getNumContrat())
                .codeProduit(entity.getCodeProduit()).idAssure(entity.getIdAssure()).statut(entity.getStatut())
                .nom(entity.getNom()).prenom(entity.getPrenom()).codePostal(entity.getCodePostal()).ville(entity.getVille())
                .pays(entity.getPays()).dateSign(entity.getDateSign()).dateVal(entity.getDateVal());

        //Si l'entity a une Epargne, on créé une instance de notre EpargneBuilder et on lui passe les valeurs de l'entity
        if (entity.getEpargne() != null) {
            Epargne epargne = Epargne.builder().numContrat(entity.getEpargne().getNumContrat())
                    .nbPart(entity.getEpargne().getNbPart()).epargneAcquise(entity.getEpargne().getEpargneAcquise()).build();

            contratBuilder.epargne(epargne);
        }

        //Si l'entity a des Mouvements, on créé une liste de Mouvement et on lui passe les valeurs de l'entity
        if (entity.getMouvements() != null) {
            List<Mouvement> mouvements = entity.getMouvements().stream().map(mouvementEntity ->
                    Mouvement.builder()
                            .numMouv(mouvementEntity.getNumMouv())
                            .nomProduit(mouvementEntity.getNomProduit())
                            .date(mouvementEntity.getDate())
                            .type(mouvementEntity.getType())
                            .montant(mouvementEntity.getMontant())
                            .statut(mouvementEntity.getStatut())
                            .build()
            ).collect(Collectors.toList());

            contratBuilder.mouvements(mouvements);
        }

        return contratBuilder.build(); //On retourne le contratBuilder buildé
    }

    public ContratEntity fromContratToContratEntity(Contrat contrat) {
        ContratEntity contratEntity = new ContratEntity();
        contratEntity.setNumContrat(contrat.getNumContrat());
        contratEntity.setCodeProduit(contrat.getCodeProduit());
        contratEntity.setIdAssure(contrat.getIdAssure());
        contratEntity.setStatut(contrat.getStatut());
        contratEntity.setNom(contrat.getNom());
        contratEntity.setPrenom(contrat.getPrenom());
        contratEntity.setCodePostal(contrat.getCodePostal());
        contratEntity.setVille(contrat.getVille());
        contratEntity.setPays(contrat.getPays());
        contratEntity.setDateSign(contrat.getDateSign());
        contratEntity.setDateVal(contrat.getDateVal());

        // Mapper Epargne
        if (contrat.getEpargne() != null) {
            EpargneEntity epargneEntity = new EpargneEntity();
            epargneEntity.setNumContrat(contrat.getEpargne().getNumContrat());
            epargneEntity.setNbPart(contrat.getEpargne().getNbPart());
            epargneEntity.setEpargneAcquise(contrat.getEpargne().getEpargneAcquise());
            contratEntity.setEpargne(epargneEntity);
        }

        // Mapper les Mouvements
        if (contrat.getMouvements() != null) {
            List<MouvementEntity> mouvementEntities = contrat.getMouvements().stream().map(mouvement -> {
                MouvementEntity mouvementEntity = new MouvementEntity();
                mouvementEntity.setNumMouv(mouvement.getNumMouv());
                mouvementEntity.setNomProduit(mouvement.getNomProduit());
                mouvementEntity.setDate(mouvement.getDate());
                mouvementEntity.setType(mouvement.getType());
                mouvementEntity.setMontant(mouvement.getMontant());
                mouvementEntity.setStatut(mouvement.getStatut());
                mouvementEntity.setContrat(contratEntity); //Ne pas oublier de set le contrat
                return mouvementEntity;
            }).collect(Collectors.toList());
            contratEntity.setMouvements(mouvementEntities);
        }

        return contratEntity;
    }

    //Pour les contrats Perp
    public ContratPerp fromContratPerpEntityToContrat(ContratPerpEntity entity) {
        //On crée une instance de notre ContratBuilder (créé par Lombok) et on lui passe les valeurs de l'entity
        ContratPerp.ContratPerpBuilder contratPerpBuilder = ContratPerp.builder().numContrat(entity.getNumContrat())
                .codeProduit(entity.getCodeProduit()).idAssure(entity.getIdAssure()).statut(entity.getStatut())
                .nom(entity.getNom()).prenom(entity.getPrenom()).codePostal(entity.getCodePostal()).ville(entity.getVille())
                .pays(entity.getPays()).dateSign(entity.getDateSign()).dateVal(entity.getDateVal()).isCnil(entity.getIsCnil());

        //Si l'entity a une Epargne, on créé une instance de notre EpargneBuilder et on lui passe les valeurs de l'entity
        if (entity.getEpargnePerp() != null) {
            EpargnePerp epargnePerp = EpargnePerp.builder().numContrat(entity.getEpargnePerp().getNumContrat())
                    .nbPart(entity.getEpargnePerp().getNbPart()).epargneAcquise(entity.getEpargnePerp().getEpargneAcquise()).build();

            contratPerpBuilder.epargnePerp(epargnePerp);
        }

        //Si l'entity a des Mouvements, on créé une liste de Mouvement et on lui passe les valeurs de l'entity
        if (entity.getMouvementsPerp() != null) {
            List<MouvementPerp> mouvementsPerp = entity.getMouvementsPerp().stream().map(mouvementEntity ->
                    MouvementPerp.builder()
                            .numMouv(mouvementEntity.getNumMouv())
                            .nomProduit(mouvementEntity.getNomProduit())
                            .date(mouvementEntity.getDate())
                            .type(mouvementEntity.getType())
                            .montant(mouvementEntity.getMontant())
                            .statut(mouvementEntity.getStatut())
                            .build()
            ).toList();

            contratPerpBuilder.mouvementsPerp(mouvementsPerp);
        }

        return contratPerpBuilder.build(); //On retourne le contratBuilder buildé
    }

    public ContratPerpEntity fromContratPerpToContratPerpEntity(ContratPerp contratPerp) {
        ContratPerpEntity contratPerpEntity = new ContratPerpEntity();
        contratPerpEntity.setNumContrat(contratPerp.getNumContrat());
        contratPerpEntity.setCodeProduit(contratPerp.getCodeProduit());
        contratPerpEntity.setIdAssure(contratPerp.getIdAssure());
        contratPerpEntity.setStatut(contratPerp.getStatut());
        contratPerpEntity.setNom(contratPerp.getNom());
        contratPerpEntity.setPrenom(contratPerp.getPrenom());
        contratPerpEntity.setCodePostal(contratPerp.getCodePostal());
        contratPerpEntity.setVille(contratPerp.getVille());
        contratPerpEntity.setPays(contratPerp.getPays());
        contratPerpEntity.setDateSign(contratPerp.getDateSign());
        contratPerpEntity.setDateVal(contratPerp.getDateVal());
        contratPerpEntity.setIsCnil(contratPerp.getIsCnil());

        // Mapper EpargnePerp
        if (contratPerp.getEpargnePerp() != null) {
            EpargnePerpEntity epargnePerpEntity = new EpargnePerpEntity();
            epargnePerpEntity.setNumContrat(contratPerp.getEpargnePerp().getNumContrat());
            epargnePerpEntity.setNbPart(contratPerp.getEpargnePerp().getNbPart());
            epargnePerpEntity.setEpargneAcquise(contratPerp.getEpargnePerp().getEpargneAcquise());
            contratPerpEntity.setEpargnePerp(epargnePerpEntity);
        }

        // Mapper les MouvementsPerp
        if (contratPerp.getMouvementsPerp() != null) {
            List<MouvementPerpEntity> mouvementPerpEntities = contratPerp.getMouvementsPerp().stream().map(mouvementPerp -> {
                MouvementPerpEntity mouvementPerpEntity = new MouvementPerpEntity();
                mouvementPerpEntity.setNumMouv(mouvementPerp.getNumMouv());
                mouvementPerpEntity.setNomProduit(mouvementPerp.getNomProduit());
                mouvementPerpEntity.setDate(mouvementPerp.getDate());
                mouvementPerpEntity.setType(mouvementPerp.getType());
                mouvementPerpEntity.setMontant(mouvementPerp.getMontant());
                mouvementPerpEntity.setStatut(mouvementPerp.getStatut());
                return mouvementPerpEntity;
            }).collect(Collectors.toList());
            contratPerpEntity.setMouvementsPerp(mouvementPerpEntities);
        }

        return contratPerpEntity;
    }

}
