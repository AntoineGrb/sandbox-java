package org.springframework.boot.java_sandbox.domain.exception;

/*
    Exception personnalisée qui sera lancée si un contrat n'est pas trouvé
 */

public class ContratNotFoundException extends RuntimeException {
    public ContratNotFoundException(Long numContrat) {
        super("Contrat non trouvé : " + numContrat);
    }
}
