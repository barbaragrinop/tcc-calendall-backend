package com.calendall.tcc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI calendallOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("CalendAll")
                .description("API do gerenciador acadêmico CalendAll, projeto de TCC do curso de Sistemas para Internet.")
                .version("v0.0.1")
                .contact(new Contact()
                        .name("Barbara Hellen, Bruna Costa e Nathália Regina"))
                .license(new License()
                        .name("Apache 2.0").url("http://springdoc.org")))

                .components(new Components()
                        .addSecuritySchemes("Bearer-Token-JWT",
                                new SecurityScheme()
                                        .name("bearer-jwt")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Insira o token JWT gerado para autenticar e realizar requisições")))

                .addSecurityItem(new SecurityRequirement().addList("Bearer-Token-JWT"));
    }
}