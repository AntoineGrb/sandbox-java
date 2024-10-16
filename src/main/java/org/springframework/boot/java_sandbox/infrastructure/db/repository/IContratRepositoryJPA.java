package org.springframework.boot.java_sandbox.infrastructure.db.repository;

import org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite.ContratEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Le repository IContratRepositoryJPA étend JpaRepository pour bénéficier des méthodes de base de JPA
 */

@Repository
public interface IContratRepositoryJPA extends JpaRepository<ContratEntity, Long> {
    //On peut ajouter ici des méthodes de recherches personnalisées en plus des natives si besoin
    List<ContratEntity> findAllByIdAssure(Long idAssure);

    List<ContratEntity> findByNomStartingWithIgnoreCase(String nom);
}
