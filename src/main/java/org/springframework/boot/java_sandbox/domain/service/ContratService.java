package org.springframework.boot.java_sandbox.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.java_sandbox.application.dto.in.ContratDTORequest;
import org.springframework.boot.java_sandbox.application.dto.in.MouvementDTORequest;
import org.springframework.boot.java_sandbox.application.dto.out.ContratDTO;
import org.springframework.boot.java_sandbox.application.mapper.ContratDTOMapper;
import org.springframework.boot.java_sandbox.application.mapper.MouvementDTOMapper;
import org.springframework.boot.java_sandbox.domain.exception.ContratNotFoundException;
import org.springframework.boot.java_sandbox.domain.model.Contrat;
import org.springframework.boot.java_sandbox.domain.model.Mouvement;
import org.springframework.boot.java_sandbox.domain.port.in.IContratService;
import org.springframework.boot.java_sandbox.domain.port.out.IContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.boot.java_sandbox.domain.utils.ContratUtils.calculerMontantTotal;

/*
    Le service est la classe qui va gérer la logique métier.
    Il implémente les méthodes du port d'entrée (l'interface IContratService).
    Il appelle le repository pour obtenir les données via le port out.
 */

@Service
@AllArgsConstructor
public class ContratService implements IContratService {

    private final IContratRepository contratRepository; //On appelle le repository via son interface (port out)
    private final MouvementDTOMapper mouvementDTOMapper;
    private final ContratDTOMapper contratDTOMapper;

    //* Rechercher un contrat par son id
    public ContratDTO getContratById(Long numContrat) { //On implémente la méthode de l'interface (port in)
        // Optional<Contrat> contrat = contratRepository.findById(numContrat); //On utilise la méthode du repository (port out) pour obtenir le contrat (modèle)
        // return contrat.orElseThrow(() -> new ContratNotFoundException(numContrat)); //On retourne le contrat (modèle) ou null

        //On utilise la méthode du repository (port out) pour obtenir le contrat (modèle)
        Contrat contrat = contratRepository.findById(numContrat)
                .orElseThrow(() -> new ContratNotFoundException(numContrat));

        //On calcule le typeContrat en fonction du code produit
        Long codeProduit = contrat.getCodeProduit();
        String typeContrat = switch (codeProduit.toString()) {
            case "101" -> "UC";
            case "102", "103" -> "Retraite";
            case "104" -> "PERP";
            default -> null;
        };

        //On calcule le montant total (somme de tous les mouvements)
        Double montantTotal = calculerMontantTotal(contrat);

        // On convertit le contrat en contratDTO et on ajoute les nouvelles données
        ContratDTO contratDTO = contratDTOMapper.fromContratToContratDTO(contrat);
        contratDTO.setTypeContrat(typeContrat);
        contratDTO.setMontantTotal(montantTotal);

        // On retourne le contratDTO
        return contratDTO;
    }

    //* Rechercher tous les contrats d'un assuré par l'idAssure
    public List<Contrat> getAllContratsByIdAssure(Long idAssure) {
        return contratRepository.findAllByIdAssure(idAssure);
    }

    //* Rechercher tous les contrats par un nom recherché
    public List<Contrat> getAllContratsBySearchedName(String searchedName) {
        return contratRepository.searchContratsByName(searchedName);
    }

    //* Ajouter un mouvement à un contrat
    public Mouvement addMouvement(Long numContrat, MouvementDTORequest mouvementDTO) {
        //Récupérer le contrat pour lequel on veut ajouter un mouvement
        Contrat contrat = contratRepository.findById(numContrat)
                .orElseThrow(() -> new ContratNotFoundException(numContrat));
        // Ici on peut utiliser orElseThrow car la méthode findById renvoie un Optional

        //Convertir le mouvement DTO reçu par la requête en modèle
        Mouvement mouvement = mouvementDTOMapper.fromMouvementDTORequestToMouvement(mouvementDTO);

        //Associer le contrat au mouvement en lui attribuant le numéro de contrat passé en paramètre
        mouvement.setNumContrat(numContrat);

        //Ajouter le mouvement au contrat
        contrat.getMouvements().add(mouvement);

        //Sauvegarder le contrat avec la méthode save du repository (port out)
        contratRepository.save(contrat);
        return mouvement;
    }

    //* Mettre à jour un contrat
    public Contrat updateContrat(Long numContrat, ContratDTORequest contratDTO) {
        //Récupérer le contrat en base (s'il existe)
        Contrat existingContrat = contratRepository.findById(numContrat)
                .orElseThrow(() -> new ContratNotFoundException(numContrat));

        //Convertir le contratDTO reçu en contrat
        Contrat contrat = contratDTOMapper.fromContratDTORequestToContrat(contratDTO);

        //Mettre à jour les valeurs du contrat existant avec le contrat reçu
        existingContrat.setNom(contrat.getNom());
        existingContrat.setPrenom(contrat.getPrenom());
        existingContrat.setStatut(contrat.getStatut());
        existingContrat.setCodePostal(contrat.getCodePostal());
        existingContrat.setVille(contrat.getVille());
        existingContrat.setPays(contrat.getPays());

        //Sauvegarder le contrat en base et le retourner
        return contratRepository.save(existingContrat);
    }

    //* Supprimer un contrat
    public void deleteContrat(Long numContrat) {
        //Récupérer le contrat en base (s'il existe)
        Contrat contrat = contratRepository.findById(numContrat)
                .orElseThrow(() -> new ContratNotFoundException(numContrat));

        //Supprimer le contrat avec la méthode delete du repository (port out)
        contratRepository.delete(numContrat);
    }
}
