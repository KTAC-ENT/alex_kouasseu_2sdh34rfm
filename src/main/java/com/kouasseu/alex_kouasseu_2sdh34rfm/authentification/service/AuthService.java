package com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service;

import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.AuthResponse;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.LoginRequest;
import com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.dto.RegisterRequest;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  27/05/2023 -- 13:36<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.authentification.service<br></br>
 */
public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authentication(LoginRequest request);
}
