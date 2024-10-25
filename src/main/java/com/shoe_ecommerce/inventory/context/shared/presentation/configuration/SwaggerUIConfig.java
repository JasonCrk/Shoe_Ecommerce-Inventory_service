package com.shoe_ecommerce.inventory.context.shared.presentation.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerUIConfig {

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shoe E-commerce - Inventory service")
                        .version("v1.0.0"));
    }
}
