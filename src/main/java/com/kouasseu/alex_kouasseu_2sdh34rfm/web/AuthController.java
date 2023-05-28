package com.kouasseu.alex_kouasseu_2sdh34rfm.web;

import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.AuthResponse;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.LoginRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RegisterRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 00:35<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.web<br></br>
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = """
            Enregistrement d'un utilisateur
            """)
    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enregistrement et génération du token pour l'utilisateur", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "L'utilisateur existe déjà dans notre système", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erreur interne qui s'est produit au niveau de l'application", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = """
            Connexion pour un utilisateur
            """)
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentification et génération du token pour l'utilisateur", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas été trouvé dans notre système", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erreur interne qui s'est produit au niveau de l'application", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authentication(request));
    }

}
