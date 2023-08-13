package com.domain.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Muhammad Naufal",
                        email = "muhammadnaufaall@gmail.com",
                        url = "https://github.com/mhmmdnaufall"
                ),
                description = "OpenAPI documentation for spring RESTful API",
                title = "OpenAPI specification - mhmmdnaufall",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "local ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
