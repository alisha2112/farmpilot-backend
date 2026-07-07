package com.example.farmpilot_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI farmPilotOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FarmPilot API")
                        .description("REST API documentation for the FarmPilot farm management system")
                        .version("1.0.0"));
    }
}