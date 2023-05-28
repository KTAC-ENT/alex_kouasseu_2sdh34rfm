package com.kouasseu.alex_kouasseu_2sdh34rfm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Copyright (c) 2023, Alex K., All Right Reserved.<br></br>
 * <a href="https://www.linkedin.com/in/alex-kouasseu/">My LinkedIn Account</a><br></br>
 * -----------------------------------<br></br>
 * When :  28/05/2023 -- 14:10<br></br>
 * By : @author alexk<br></br>
 * Project : alex_kouasseu_2sdh34rfm<br></br>
 * Package : com.kouasseu.alex_kouasseu_2sdh34rfm.config<br></br>
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Alex KOUASSEU",
                        email = "alex@kouasseu.com",
                        url = "https://www.linkedin.com/in/alex-kouasseu/"
                ),
                description = "Documentation pour ce projet test Java proposé par Anywr",
                title = "Test Java Anywr - Alex K.",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                    description = "Local ENV",
                    url = "http://localhost:8008"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Authentification JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPiConfig {
}
