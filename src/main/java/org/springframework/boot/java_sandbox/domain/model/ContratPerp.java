package org.springframework.boot.java_sandbox.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/*
    Le modèle Contrat permet de définir les données métier que l'on va manipuler dans l'application.
    C'est cet objet qui est retourné par le service et qui est utilisé par le contrôleur.
 */

@Setter
@Getter
@SuperBuilder
public class ContratPerp extends Contrat {
    private Boolean isCnil;
    private EpargnePerp epargnePerp;
    private List<MouvementPerp> mouvementsPerp;
}


