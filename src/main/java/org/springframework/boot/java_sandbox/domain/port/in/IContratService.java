package org.springframework.boot.java_sandbox.domain.port.in;

import org.springframework.boot.java_sandbox.application.dto.in.ContratDTORequest;
import org.springframework.boot.java_sandbox.application.dto.in.MouvementDTORequest;
import org.springframework.boot.java_sandbox.application.dto.out.ContratDTO;
import org.springframework.boot.java_sandbox.domain.model.Contrat;
import org.springframework.boot.java_sandbox.domain.model.Mouvement;

import java.util.List;

/*
    Le port in IContratService permet de définir les méthodes que devra implémenter la classe ContratService
    C'est cette interface qui est appelée par le contrôleur pour obtenir les données.
 */

public interface IContratService {
    ContratDTO getContratById(Long id);

    List<Contrat> getAllContratsByIdAssure(Long idAssure);

    List<Contrat> getAllContratsBySearchedName(String searchedName);

    Mouvement addMouvement(Long numContrat, MouvementDTORequest mouvementDTO);

    Contrat updateContrat(Long numContrat, ContratDTORequest contratDTO);

    void deleteContrat(Long numContrat);
}
