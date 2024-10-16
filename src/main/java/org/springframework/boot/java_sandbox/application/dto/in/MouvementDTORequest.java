package org.springframework.boot.java_sandbox.application.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
    Ce DTO décrit l'objet Mouvement reçu lors de la requête POST
 */

@Getter
@Setter
@Builder
public class MouvementDTORequest {
    private String nomProduit;
    private String date;
    private String type;
    private Double montant;
    private String statut;
}
