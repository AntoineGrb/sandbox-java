package org.springframework.boot.java_sandbox.application.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.java_sandbox.application.dto.in.ContratDTORequest;
import org.springframework.boot.java_sandbox.application.dto.in.MouvementDTORequest;
import org.springframework.boot.java_sandbox.application.dto.out.ContratDTO;
import org.springframework.boot.java_sandbox.application.mapper.ContratDTOMapper;
import org.springframework.boot.java_sandbox.application.mapper.MouvementDTOMapper;
import org.springframework.boot.java_sandbox.domain.model.Contrat;
import org.springframework.boot.java_sandbox.domain.model.ContratPerp;
import org.springframework.boot.java_sandbox.domain.model.Mouvement;
import org.springframework.boot.java_sandbox.domain.port.in.IContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    Le contrôleur est la classe qui va gérer les requêtes HTTP.
    Il appelle les services via les interfaces pour obtenir les données,
    et utilise le mapper pour convertir les données entre DTO et modèle.
 */

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("api") //Le endpoint de la requête
public class ContratController {

    private final IContratService contratService; //On appelle l'interface du service, implémentée par le service
    private final ContratDTOMapper contratDTOMapper; //On appelle le mapper pour convertir les données
    private final MouvementDTOMapper mouvementDTOMapper;

    //*Rechercher un contrat par son id
    @GetMapping("/contrat/{id}") //On précise le type GET et le paramètre attendu
    public ContratDTO getContratById(@PathVariable Long id) { //Ici on veut obtenir un contrat via son id
        Contrat contrat = contratService.getContratById(id); //On utilise la méthode du service pour obtenir le contrat (modèle)

        // Vérification du type de contrat pour choisir le mapper
        if (contrat instanceof ContratPerp) {
            return contratDTOMapper.fromContratPerpToContratDTO((ContratPerp) contrat); // Conversion spécifique à ContratPerp
        } else {
            return contratDTOMapper.fromContratToContratDTO(contrat); // Conversion spécifique à Contrat normal
        }
    }

    //*Rechercher tous les contrats d'un assuré par l'idAssure
    @GetMapping("/assure/{idAssure}") //On précise le type GET et le paramètre attendu
    public List<ContratDTO> getAllContratsByIdAssure(@PathVariable Long idAssure) { //Ici on veut obtenir tous
        List<Contrat> contrats = contratService.getAllContratsByIdAssure(idAssure);

        // Utiliser le bon mapper pour chaque type de contrat
        return contrats.stream()
                .map(contrat -> {
                    if (contrat instanceof ContratPerp) {
                        return contratDTOMapper.fromContratPerpToContratDTO((ContratPerp) contrat);
                    } else {
                        return contratDTOMapper.fromContratToContratDTO(contrat);
                    }
                })
                .toList();
    }

    //*Rechercher tous les contrats en recherchant par le nom
    @GetMapping("/contrats/search")
    public ResponseEntity<List<ContratDTO>> searchContratsByName(@RequestParam("name") String name) {
        List<Contrat> contrats = contratService.getAllContratsBySearchedName(name);
        List<ContratDTO> contratDTOS = contrats.stream().map(contratDTOMapper::fromContratToContratDTO).toList();
        return ResponseEntity.ok(contratDTOS);
    }

    //*Ajouter un mouvement à un contrat
    @PostMapping("/contrat/{numContrat}/addMouvement")
    public ResponseEntity<MouvementDTORequest> addMouvement(
            @PathVariable Long numContrat,
            @RequestBody MouvementDTORequest mouvementDTO) {

        // Appel de la méthode du service pour ajouter un mouvement
        Mouvement mouvement = contratService.addMouvement(numContrat, mouvementDTO);

        //Retourner la réponse
        return ResponseEntity.status(HttpStatus.CREATED).body(mouvementDTOMapper.fromMouvementToMouvementDTORequest(mouvement));
    }

    //*Mettre à jour un contrat
    @PutMapping("/contrat/{numContrat}/update")
    public ResponseEntity<ContratDTORequest> updateContrat(
            @PathVariable Long numContrat,
            @Valid @RequestBody ContratDTORequest contratDTORequest
    ) {

        //Appeler la méthode update du service
        Contrat contrat = contratService.updateContrat(numContrat, contratDTORequest);

        //Retourner la réponse format DTO
        return ResponseEntity.status(HttpStatus.OK).body(contratDTOMapper.fromContratToContratDTORequest(contrat));
    }

    //*Supprimer un contrat
    @DeleteMapping("/contrat/{numContrat}/delete")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long numContrat) {

        //Appeler la méthode delete du service
        contratService.deleteContrat(numContrat);

        //Retourner la réponse
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
