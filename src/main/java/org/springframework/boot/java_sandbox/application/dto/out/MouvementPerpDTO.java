package org.springframework.boot.java_sandbox.application.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MouvementPerpDTO {
    private Long numMouv;
    private String nomProduit;
    private String date;
    private String type;
    private Double montant;
    private String statut;
}
