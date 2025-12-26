package com.springboot.jobseeker.shared.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customerAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Seeker Management API")
                        .version("1.00")
                        .description("This is a RestFul API")
                        .contact(new Contact()
                                .name("NMJ")
                                .email("nmj@gmail.com")
                                .url("https://github.com/nmj6001")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/license/LICENSE-2.0")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }
}