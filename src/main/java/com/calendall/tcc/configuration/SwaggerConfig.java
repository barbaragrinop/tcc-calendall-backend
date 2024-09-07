package com.calendall.tcc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;

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
          .name("Apache 2.0").url("http://springdoc.org")));
  }
}