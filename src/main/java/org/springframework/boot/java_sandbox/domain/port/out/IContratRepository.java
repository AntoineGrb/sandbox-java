package org.springframework.boot.java_sandbox.domain.port.out;

import org.springframework.boot.java_sandbox.domain.model.Contrat;

import java.util.List;
import java.util.Optional;

/*
    Le port out IContratRepository permet de définir les méthodes que devra implémenter l'adapter ContratRepositoryAdapter
    C'est cette interface qui est appelée par le service pour obtenir les données.
 */

public interface IContratRepository {
    Optional<Contrat> findById(Long id);

    //Trouver tous les contrats d'un assuré par l'id_assuré
    List<Contrat> findAllByIdAssure(Long idAssure);

    //Trouver tous les contrats en recherchant par un nom
    List<Contrat> searchContratsByName(String searchedName);

    Contrat save(Contrat contrat);

    void delete(Long id);
}
