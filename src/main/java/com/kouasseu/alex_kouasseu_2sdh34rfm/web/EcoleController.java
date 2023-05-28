package com.kouasseu.alex_kouasseu_2sdh34rfm.web;

import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantDto;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantFilter;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service.EcoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 11:50<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.web<br></br>
 */
@RestController
@RequestMapping("/api/v1/ecole")
@AllArgsConstructor
@Tag(name = "Ecole")
public class EcoleController {

    private EcoleService ecoleService;

    @Operation(summary = """
            Obtenir la liste des étudiants avec les éléments suivants :
            Filtres : Nom de la classe et/ou Nom complet de l'enseignant
            Tous les étudiants de la liste seront retournés en cas d'absence de valeur de filtres
            Paginé
            """)
    @PostMapping("/etudiant/filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "403", description = "La ressource n'est pas authorisée pour la personne qui la requête",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne qui s'est produit au niveau de l'application",
                    content = @Content)
    })
    public ResponseEntity<Page<EtudiantDto>> getEtudiantByFilter(@RequestBody EtudiantFilter filter) {
        return ResponseEntity.ok(ecoleService.filterEtudiant(filter));
    }
}
