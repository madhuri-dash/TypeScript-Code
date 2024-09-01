package org.example.shippingservicespringbootk8s.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Shipping Services From Local System : Madhuri Dash"))
                .addSecurityItem(new SecurityRequirement().addList("Shipping Services From Local System : Madhuri Dash"))
                .components(new Components().addSecuritySchemes("Shipping Services From Local System : Madhuri Dash", new SecurityScheme()
                        .name("Shipping Services From Local System : Madhuri Dash").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}