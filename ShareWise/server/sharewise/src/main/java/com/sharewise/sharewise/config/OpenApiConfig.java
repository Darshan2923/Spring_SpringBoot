package com.sharewise.sharewise.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Darshan", email = "walnut2918@gmail.com"), description = "OpenApi documentation for Spring security", title = "Openapi specification - Darshan", version = "1.0", license = @License(name = "MIT License", url = "https://mit-license.com"), termsOfService = "Terms of service"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8088/api/v1"),
        @Server(description = "PROD ENV", url = "https://darshancoding.com/courses")
}, security = {
        @SecurityRequirement(name = "bearerAuth")
})
@SecurityScheme(name = "bearerAuth", description = "JWT Auth Descrition", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

}
