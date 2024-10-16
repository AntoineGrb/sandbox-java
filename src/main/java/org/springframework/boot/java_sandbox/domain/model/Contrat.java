package org.springframework.boot.java_sandbox.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/*
    Le modèle Contrat permet de définir les données métier que l'on va manipuler dans l'application.
    C'est cet objet qui est retourné par le service et qui est utilisé par le contrôleur.
 */

@Setter
@Getter
@SuperBuilder
public class Contrat {
    private Long numContrat;
    private Long codeProduit;
    private Long idAssure;
    private String statut;
    private String nom;
    private String prenom;
    private String codePostal;
    private String ville;
    private String pays;
    private Date dateSign;
    private Date dateVal;
    private Epargne epargne; //Provient du modèle Epargne
    private List<Mouvement> mouvements; //Une liste de mouvements provenant du modèle Mouvement

}


