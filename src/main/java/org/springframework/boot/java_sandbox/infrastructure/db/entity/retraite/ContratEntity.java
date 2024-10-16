package org.springframework.boot.java_sandbox.infrastructure.db.entity.retraite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/*
    L'entité Contrat permet de définir la table contrat dans la base de données.
    C'est cet objet qui est utilisé pour manipuler les données de la table contrat.
 */

@Setter
@Getter
@Entity
@Table(name = "contrat")
public class ContratEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_contrat", unique = true, updatable = false, nullable = false)
    private Long numContrat;

    @Column(name = "code_produit")
    private Long codeProduit;

    @Column(name = "id_assure")
    private Long idAssure;

    @Column(name = "statut")
    private String statut;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    @Column(name = "date_sign")
    @Temporal(TemporalType.DATE)
    private Date dateSign;

    @Column(name = "date_val")
    @Temporal(TemporalType.DATE)
    private Date dateVal;

    @OneToOne(mappedBy = "contrat", cascade = CascadeType.ALL) //Relation avec la table epargne
    private EpargneEntity epargne; //L'objet EpargneEntity

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL) //Relation avec la table mouvement
    private List<MouvementEntity> mouvements; //L'objet MouvementEntity (une liste de mouvements)
}
