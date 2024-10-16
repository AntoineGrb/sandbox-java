package org.springframework.boot.java_sandbox.application.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
    Ce DTO décrit l'objet Contrat reçu lors d'une requête PUT pour modifier un contrat.
    Il ne comporte que les données pouvant être mises à jour.
 */

@Builder
@Setter
@Getter
public class ContratDTORequest {
    /*
        On utilise les contraintes de validation de jakarta pour cadrer la requête reçue
     */
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le statut est obligatoire")
    private String statut;

    @NotBlank(message = "Le codePostal est obligatoire")
    @Pattern(regexp = "\\d{5}", message = "Le code postal doit être composé de 5 chiffres")
    private String codePostal;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    @NotBlank(message = "Le pays est obligatoire")
    private String pays;
}
