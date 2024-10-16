package org.springframework.boot.java_sandbox.application.dto.out;

/*
Ici le DTO est une version simplifiée de l'objet Contrat, avec uniquement les données qu'on veut retournées à l'utilisateur.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ContratDTO {
    private Long numContrat;
    private String nom;
    private String prenom;
    private String statut;
    private String codePostal;
    private String ville;
    private String pays;
    private Long codeProduit;
    private Long idAssure;
    private EpargneDTO epargne; //On prend l'objet epargne pour le mettre dans l'objet contrat
    private List<MouvementDTO> mouvements; //On prend une liste d'objets mouvement pour le mettre dans l'objet contra
}
