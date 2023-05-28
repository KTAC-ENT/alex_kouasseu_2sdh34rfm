package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service;

import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantDto;
import com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto.EtudiantFilter;
import org.springframework.data.domain.Page;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 02:29<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.service<br></br>
 */
public interface EcoleService {
    Page<EtudiantDto> filterEtudiant(EtudiantFilter filter);
}
