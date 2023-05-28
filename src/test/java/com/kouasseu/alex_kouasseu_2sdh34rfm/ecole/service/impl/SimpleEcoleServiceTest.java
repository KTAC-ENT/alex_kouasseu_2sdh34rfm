package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service.impl;

import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dao.EtudiantRepository;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantDto;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantFilter;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities.Enseignant;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities.Etudiant;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities.SalleClasse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 11:56<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service.impl<br></br>
 */
class SimpleEcoleServiceTest {

    @InjectMocks
    SimpleEcoleService simpleEcoleService;
    @Mock
    EtudiantRepository etudiantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void filterEtudiant_having_noFilter() {
        //Given
        EtudiantFilter filter = EtudiantFilter.builder()
                .fullNameEnseignant(null)
                .nomClasse(null)
                .page(1)
                .size(10)
                .build();
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());

        Page<Etudiant> etudiantPage = new PageImpl<>(List.of(
                Etudiant.builder().id(1L).nom("ETU01").prenom("ETU01").salleClasse(SalleClasse.builder().enseignant(Enseignant.builder().prenom("PRENOM").build()).build()).build()
                ));

        //When
        when(etudiantRepository.findAll(pageable)).thenReturn(etudiantPage);
        Page<EtudiantDto> result = simpleEcoleService.filterEtudiant(filter);

        //Then
        assertNotNull(result);
        assertEquals(result.getSize(), 1);
    }

    @Test
    void filterEtudiant_having_nomClassFilter() {
        //Given
        EtudiantFilter filter = EtudiantFilter.builder()
                .fullNameEnseignant(null)
                .nomClasse("SALL001")
                .page(1)
                .size(10)
                .build();
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());

        Page<Etudiant> etudiantPage = new PageImpl<>(List.of(
                Etudiant.builder()
                        .id(1L)
                        .nom("ETU01")
                        .prenom("ETU01")
                        .salleClasse(SalleClasse.builder()
                                .nom("SALL001")
                                .enseignant(Enseignant.builder().prenom("PRENOM").build())
                                .build())
                        .build()
        ));
        when(etudiantRepository.findBySalleClasseNomContaining(filter.getNomClasse(), pageable)).thenReturn(etudiantPage);

        //When
        Page<EtudiantDto> result = simpleEcoleService.filterEtudiant(filter);

        //Then
        assertNotNull(result);
        assertEquals(result.getSize(), 1);
    }
    @Test
    void filterEtudiant_having_fullNameEnseignantFilter() {
        //Given
        EtudiantFilter filter = EtudiantFilter.builder()
                .fullNameEnseignant("PRE001 ENS001")
                .nomClasse(null)
                .page(1)
                .size(10)
                .build();
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());

        Page<Etudiant> etudiantPage = new PageImpl<>(List.of(
                Etudiant.builder()
                        .id(1L)
                        .nom("ETU01")
                        .prenom("ETU01")
                        .salleClasse(SalleClasse.builder()
                                .nom("SALL001")
                                .enseignant(Enseignant.builder()
                                        .prenom("PRE001")
                                        .nom("ENS001").build())
                                .build())
                        .build()
        ));
        when(etudiantRepository.findByEnseigantFullName(filter.getFullNameEnseignant(), pageable)).thenReturn(etudiantPage);

        //When
        Page<EtudiantDto> result = simpleEcoleService.filterEtudiant(filter);

        //Then
        assertNotNull(result);
        assertEquals(result.getSize(), 1);
    }

    @Test
    void filterEtudiant_having_allFilter() {
        //Given
        EtudiantFilter filter = EtudiantFilter.builder()
                .fullNameEnseignant("PRE001 ENS001")
                .nomClasse("SALL001")
                .page(1)
                .size(10)
                .build();
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());

        Page<Etudiant> etudiantPage = new PageImpl<>(List.of(
                Etudiant.builder()
                        .id(1L)
                        .nom("ETU01")
                        .prenom("ETU01")
                        .salleClasse(SalleClasse.builder()
                                .nom("SALL001")
                                .enseignant(Enseignant.builder()
                                        .prenom("PRE001")
                                        .nom("ENS001").build())
                                .build())
                        .build()
        ));
        when(etudiantRepository.findBySalleClasseAndEnseigantFullName(filter.getNomClasse(), filter.getFullNameEnseignant(), pageable)).thenReturn(etudiantPage);

        //When
        Page<EtudiantDto> result = simpleEcoleService.filterEtudiant(filter);

        //Then
        assertNotNull(result);
        assertEquals(result.getSize(), 1);
    }
}