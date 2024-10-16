package org.springframework.boot.java_sandbox.application.dto.out;

/*
Ici le DTO est une version simplifiée de l'objet Contrat, avec uniquement les données qu'on veut retournées à l'utilisateur.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ContratPerpDTO extends ContratDTO {
    private boolean isCnil;
    private EpargnePerpDTO epargnePerp; //On prend l'objet epargne pour le mettre dans l'objet contrat
    private List<MouvementPerpDTO> mouvementsPerp; //On prend une liste d'objets mouvement pour le mettre dans l'objet contrat

    @Override
    @JsonIgnore
    public EpargneDTO getEpargne() {
        return null; // Ignorer le champ epargne hérité
    }

    @Override
    @JsonIgnore
    public List<MouvementDTO> getMouvements() {
        return null; // Ignorer le champ mouvements hérité
    }

}
