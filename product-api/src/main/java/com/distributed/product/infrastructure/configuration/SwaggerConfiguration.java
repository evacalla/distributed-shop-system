package com.distributed.product.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfiguration {
    private static final String API_TITLE = "product-api";
    private static final String API_DESCRIPTION = "Endpoints documentation";
    private static final String API_VERSION = "1.0.0";
    private static final String API_CONTACT_NAME = "Test";
    private static final String API_CONTACT_URL = "www.test.com.ar";
    private static final String API_CONTACT_EMAIL = "test@test.com.ar";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION)
                        .contact(new Contact()
                                .name(API_CONTACT_NAME)
                                .url(API_CONTACT_URL)
                                .email(API_CONTACT_EMAIL)));
    }
}
