package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service.impl;

import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dao.EtudiantRepository;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantDto;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantFilter;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities.Etudiant;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service.EcoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 03:08<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service<br></br>
 */
@Service
@AllArgsConstructor
public class SimpleEcoleService implements EcoleService {
    private EtudiantRepository etudiantRepository;
    @Override
    public Page<EtudiantDto> filterEtudiant(EtudiantFilter filter) {
       Page<Etudiant> result;
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize() == 0 ? 10 : filter.getSize());
        if (Boolean.logicalAnd(Objects.isNull(filter.getNomClasse()), Objects.isNull(filter.getFullNameEnseignant()))) {
            result = etudiantRepository.findAll(pageable);
        } else if (filter.getFullNameEnseignant() != null && filter.getNomClasse() == null) {
            result = etudiantRepository.findByEnseigantFullName(filter.getFullNameEnseignant(), pageable);
        } else if (filter.getNomClasse() != null && filter.getFullNameEnseignant() == null) {
            result = etudiantRepository.findBySalleClasseNomContaining(filter.getNomClasse(), pageable);
        }else {
            result = etudiantRepository.findBySalleClasseAndEnseigantFullName(filter.getNomClasse(),filter.getFullNameEnseignant(), pageable);
        }
        return result.map(buildEtudiantDto());
    }

    Function<Etudiant, EtudiantDto> buildEtudiantDto() {
        return etudiant -> EtudiantDto.builder()
                .id(etudiant.getId())
                .prenom(etudiant.getPrenom())
                .nom(etudiant.getNom())
                .nomClasse(etudiant.getSalleClasse().getNom())
                .nomEnseignant(etudiant.getSalleClasse().getEnseignant().getPrenom().concat(" "+etudiant.getSalleClasse().getEnseignant().getNom()))
                .build();
    }
}
