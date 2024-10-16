package org.springframework.boot.java_sandbox.domain.utils;

import org.springframework.boot.java_sandbox.domain.model.Contrat;
import org.springframework.boot.java_sandbox.domain.model.Mouvement;

/*
    La classe ContratUtils contient des méthodes utilitaires pour les contrats.
 */

public class ContratUtils {

    //La méthode est static pour pouvoir être appelée sans instancier la classe
    public static Double calculerMontantTotal(Contrat contrat) {
        return contrat
                .getMouvements()
                .stream()
                .mapToDouble(Mouvement::getMontant)
                .sum();
    }
}
