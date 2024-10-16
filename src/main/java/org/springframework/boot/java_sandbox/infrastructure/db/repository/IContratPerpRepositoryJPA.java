package org.springframework.boot.java_sandbox.infrastructure.db.repository;

import org.springframework.boot.java_sandbox.infrastructure.db.entity.perp.ContratPerpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Le repository IContratRepositoryJPA étend JpaRepository pour bénéficier des méthodes de base de JPA
 */

@Repository
public interface IContratPerpRepositoryJPA extends JpaRepository<ContratPerpEntity, Long> {
    //On peut ajouter ici des méthodes de recherches personnalisées en plus des natives si besoin
    List<ContratPerpEntity> findAllByIdAssure(Long idAssure);
}
