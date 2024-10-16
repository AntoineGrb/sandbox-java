package org.springframework.boot.java_sandbox.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MouvementPerp {
    private Long numMouv;
    private Long numContrat;
    private String nomProduit;
    private String date;
    private String type;
    private Double montant;
    private String statut;
}
