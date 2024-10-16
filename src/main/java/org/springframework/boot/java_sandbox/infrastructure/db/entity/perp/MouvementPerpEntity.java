package org.springframework.boot.java_sandbox.infrastructure.db.entity.perp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mouvementperp")
public class MouvementPerpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_mouv", nullable = false, unique = true)
    private Long numMouv;

    @ManyToOne //Relation avec la table contrat
    @JoinColumn(name = "num_contrat") //La jointure sur la clé étrangère
    private ContratPerpEntity contratPerp; //L'objet ContratEntity
    //? Pas besoin de déclarer à nouveau le num_contrat dans le cas d'une many to one

    @Column(name = "nom_produit")
    private String nomProduit;

    @Column(name = "date")
    private String date;

    @Column(name = "type")
    private String type;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "statut")
    private String statut;
}
