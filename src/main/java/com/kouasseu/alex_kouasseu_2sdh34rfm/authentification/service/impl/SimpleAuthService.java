package com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.impl;

import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dao.UserRepository;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.AuthResponse;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.LoginRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RegisterRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.entities.User;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.exception.AuthException;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.exception.RegisterException;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.AuthService;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  27/05/2023 -- 13:38<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.impl<br></br>
 */
@Service
@RequiredArgsConstructor
public class SimpleAuthService implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RegisterException(String.format("L'utilisateur %s existe déjà", request.getEmail()));
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = userRepository.save(user);
        return AuthResponse.builder()
                .token(tokenService.generateToken(savedUser))
                .build();
    }

    @Override
    public AuthResponse authentication(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException(String.format("User -> %s, n'a pas été trouvé", request.getEmail())));
        return AuthResponse.builder()
                .token(tokenService.generateToken(user))
                .build();
    }
}
