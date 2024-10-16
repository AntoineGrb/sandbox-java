package org.springframework.boot.java_sandbox.infrastructure.db.entity.perp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "epargneperp")
public class EpargnePerpEntity {
    @Id
    @Column(name = "num_contrat")
    private Long numContrat;

    @OneToOne //Relation avec la table contratPerp
    @JoinColumn(name = "num_contrat")
    private ContratPerpEntity contratPerp;

    @Column(name = "nb_part")
    private Integer nbPart;

    @Column(name = "epargne_acquise")
    private Double epargneAcquise;
}
