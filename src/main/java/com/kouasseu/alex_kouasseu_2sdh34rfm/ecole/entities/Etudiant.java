package com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 01:27<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.ecole.entities<br></br>
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Etudiant {
    @Id
    @GeneratedValue
    private Long id;
    private String prenom;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "salle_classe_id")
    private SalleClasse salleClasse;
}
