package com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.impl;

import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dao.UserRepository;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.AuthResponse;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RegisterRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RoleEnum;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.entities.User;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.exception.RegisterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  27/05/2023 -- 13:40<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.impl<br></br>
 */

class SimpleAuthServiceTest {

    @InjectMocks
    SimpleAuthService authService;

    @Mock
    UserRepository userRepository;

    @Mock
    JwtTokenService tokenService;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_with_nonExisting_User() {
        //Given
        RegisterRequest request = RegisterRequest.builder()
                .email("xyz@example.com")
                .password("password")
                .role(RoleEnum.ENSEIGNANT)
                .build();

        String pwdEncoded = "passwordEncoded";

        User saveUser = User.builder()
                .id(null)
                .email("xyz@example.com")
                .password(pwdEncoded)
                .role(RoleEnum.ENSEIGNANT)
                .build();

        String generatedToke = "dklsmdkmsldkslmdksm.klskdsmldksldksmd";

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.FALSE);
        when(passwordEncoder.encode(request.getPassword())).thenReturn(pwdEncoded);
        when(userRepository.save(saveUser)).thenReturn(saveUser);
        when(tokenService.generateToken(saveUser)).thenReturn(generatedToke);

        //When
        AuthResponse authResponse = authService.register(request);

        //Then
        assertNotNull(authResponse);
        verify(userRepository).existsByEmail(request.getEmail());
        verify(userRepository).save(any(User.class));
    }


    @Test
    void register_with_existing_User() {
        //Given
        RegisterRequest request = RegisterRequest.builder()
                .email("xyz@example.com")
                .password("password")
                .role(RoleEnum.ENSEIGNANT)
                .build();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.TRUE);

        Exception exception = assertThrows(RegisterException.class, () -> {
            authService.register(request);
        });

        String expectedMessage = "L'utilisateur xyz@example.com existe déjà";
        String actualMessage = exception.getMessage();

        //Then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}