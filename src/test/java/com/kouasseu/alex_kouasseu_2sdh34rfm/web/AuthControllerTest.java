package com.kouasseu.alex_kouasseu_2sdh34rfm.web;

import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.AuthResponse;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.LoginRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RegisterRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RoleEnum;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.exception.AuthException;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service.impl.SimpleAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 13:28<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.web<br></br>
 */
class AuthControllerTest {
    @InjectMocks
    AuthController authController;

    @Mock
    SimpleAuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() {
        //Given
        RegisterRequest request = RegisterRequest.builder()
                .email("xyz@example.com")
                .password("password")
                .role(RoleEnum.ENSEIGNANT)
                .build();

        String generatedToke = "dklsmdkmsldkslmdksm.klskdsmldksldksmd";

        AuthResponse authResponse = AuthResponse.builder()
                .token(generatedToke)
                .build();

        when(authService.register(request)).thenReturn(authResponse);

        //When

        ResponseEntity<AuthResponse> result = authController.register(request);

        //Then
        assertTrue(result.hasBody());
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertEquals(result.getBody(), authResponse);
        verify(authService, times(1)).register(request);
    }


    @Test
    void authenticate() {
        //Given
        LoginRequest request = LoginRequest.builder()
                .email("xyz@example.com")
                .password("password")
                .build();

        String generatedToke = "dklsmdkmsldkslmdksm.klskdsmldksldksmd";

        AuthResponse authResponse = AuthResponse.builder()
                .token(generatedToke)
                .build();

        when(authService.authentication(request)).thenReturn(authResponse);

        //When

        ResponseEntity<AuthResponse> result = authController.authenticate(request);

        //Then
        assertTrue(result.hasBody());
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertEquals(result.getBody(), authResponse);
        verify(authService, times(1)).authentication(request);
    }


    @Test
    void authenticate_unAuthorized() {
        //Given
        LoginRequest request = LoginRequest.builder()
                .email("xyz@example.com")
                .password("wrongpassword")
                .build();

        when(authService.authentication(request)).thenThrow(AuthException.class);

        //When
        Exception exception = assertThrows(AuthException.class, () -> {
            authController.authenticate(request);
        });

        //Then
        assertNotNull(exception);
        verify(authService, times(1)).authentication(request);
    }

}