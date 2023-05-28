package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dao;

import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities.SalleClasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 02:25<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dao<br></br>
 */
@Repository
public interface SalleClassRepository extends JpaRepository<SalleClasse, Long> {
}
