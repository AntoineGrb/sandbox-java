package org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "epargne")
public class EpargneEntity {
    @Id
    @Column(name = "num_contrat")
    private Long numContrat;

    @OneToOne //Relation avec la table contrat
    @JoinColumn(name = "num_contrat")
    private ContratEntity contrat;

    @Column(name = "nb_part")
    private Integer nbPart;

    @Column(name = "epargne_acquise")
    private Double epargneAcquise;
}
