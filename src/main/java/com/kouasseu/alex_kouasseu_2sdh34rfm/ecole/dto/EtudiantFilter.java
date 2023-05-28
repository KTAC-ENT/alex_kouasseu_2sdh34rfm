package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 02:33<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.dto<br></br>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantFilter implements Serializable {
    private String nomClasse;
    private String fullNameEnseignant;
    private int page;
    private int size;
}
