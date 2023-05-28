package com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  27/05/2023 -- 13:33<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.model<br></br>
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Email null: L'email ne doit pas être null")
    @NotBlank(message = "Email vide: L'email ne doit pas être vide")
    @Email(message = "Ce champs doit comporter une email sous forme xyz@example.com")
    private String email;
    @NotNull(message = "Password null: L'email ne doit pas être null")
    @NotBlank(message = "Password vide: L'email ne doit pas être vide")
    private String password;
    @NotNull(message = "Role null: Le role ne doit pas être null")
    private RoleEnum role;
}
