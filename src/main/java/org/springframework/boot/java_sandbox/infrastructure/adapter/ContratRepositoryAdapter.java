package org.springframework.boot.java_sandbox.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.springframework.boot.java_sandbox.domain.model.Contrat;
import org.springframework.boot.java_sandbox.domain.model.ContratPerp;
import org.springframework.boot.java_sandbox.domain.port.out.IContratRepository;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.perp.ContratPerpEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite.ContratEntity;
import org.springframework.boot.java_sandbox.infrastructure.db.mapper.ContratEntityMapper;
import org.springframework.boot.java_sandbox.infrastructure.db.repository.IContratPerpRepositoryJPA;
import org.springframework.boot.java_sandbox.infrastructure.db.repository.IContratRepositoryJPA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/*
    L'adapter ContratRepositoryAdapter implémente le port out IContratRepository
    Il permet de faire le lien entre le service et le repository
    Il appelle les méthodes du repository pour obtenir les données
 */

@Component
@AllArgsConstructor
public class ContratRepositoryAdapter implements IContratRepository {

    private final IContratRepositoryJPA contratRepositoryJPA; //On appelle le repository via son interface
    private final IContratPerpRepositoryJPA contratPerpRepositoryJPA;
    private final ContratEntityMapper contratMapper; //On appelle le mapper pour convertir les données

    //Trouver un contrat par id_contrat
    @Override
    public Optional<Contrat> findById(Long id) {
        Optional<ContratEntity> entity = contratRepositoryJPA.findById(id); //On utilise la méthode du repository pour obtenir le contrat (entité)
        return entity.map(contratMapper::fromContratEntityToContrat); //On convertit le contrat (entité) en modèle via le mapper et on le retourne
        //Ici .map est une méthode de l'objet Optional qui permet de faire une action si l'objet n'est pas null
    }

    //Trouver tous les contrats d'un assuré par l'id_assuré
    @Override
    public List<Contrat> findAllByIdAssure(Long idAssure) {
        //On récupère les contrats classiques
        List<ContratEntity> contratEntities = contratRepositoryJPA.findAllByIdAssure(idAssure);
        List<Contrat> contrats = new java.util.ArrayList<>(contratEntities.stream().map(contratMapper::fromContratEntityToContrat).toList());

        //On récupère les contrats retraite
        List<ContratPerpEntity> contratPerpEntities = contratPerpRepositoryJPA.findAllByIdAssure(idAssure);
        List<ContratPerp> contratsPerp = contratPerpEntities.stream().map(contratMapper::fromContratPerpEntityToContrat).toList();

        //On fusionne le tout
        contrats.addAll(contratsPerp);
        return contrats;
    }

    //Trouver tous les contrats en recherchant par un nom
    @Override
    public List<Contrat> searchContratsByName(String searchedName) {
        List<ContratEntity> entities = contratRepositoryJPA.findByNomStartingWithIgnoreCase(searchedName);
        return entities.stream().map(contratMapper::fromContratEntityToContrat).toList();
    }

    //Sauvegarder ou mettre à jour un contrat
    @Override
    public Contrat save(Contrat contrat) {
        if (contrat instanceof ContratPerp) {
            //On convertit le contrat en entité
            ContratPerpEntity entity = contratMapper.fromContratPerpToContratPerpEntity((ContratPerp) contrat);
            return contratMapper.fromContratPerpEntityToContrat(contratPerpRepositoryJPA.save(entity));
        } else {
            ContratEntity entity = contratMapper.fromContratToContratEntity(contrat);
            return contratMapper.fromContratEntityToContrat(contratRepositoryJPA.save(entity));
        }
    }

    //Supprimer un contrat
    @Override
    public void delete(Long id) {
        contratRepositoryJPA.deleteById(id);
    }
}
