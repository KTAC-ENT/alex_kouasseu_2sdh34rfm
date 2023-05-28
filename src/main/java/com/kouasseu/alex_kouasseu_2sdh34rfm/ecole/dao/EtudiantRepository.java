package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dao;

import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 02:24<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dao<br></br>
 */

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Page<Etudiant> findBySalleClasseNomContaining(String nom, Pageable pageable);
    @Query("SELECT e FROM Etudiant e WHERE CONCAT(e.salleClasse.enseignant.prenom, ' ', e.salleClasse.enseignant.nom) LIKE CONCAT('%', :fullName, '%')")
    Page<Etudiant> findByEnseigantFullName(@Param("fullName") String fullNameEnseignant, Pageable pageable);
    @Query("SELECT e FROM Etudiant e WHERE (e.salleClasse.nom LIKE CONCAT('%', :salleName,'%')) AND (CONCAT(e.salleClasse.enseignant.prenom, ' ', e.salleClasse.enseignant.nom) LIKE CONCAT('%', :fullName, '%'))")
    Page<Etudiant> findBySalleClasseAndEnseigantFullName(@Param("salleName") String salleClasseName, @Param("fullName") String fullNameEnseignant, Pageable pageable);
}
