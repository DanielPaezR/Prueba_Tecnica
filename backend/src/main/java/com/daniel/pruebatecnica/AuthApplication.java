package com.daniel.pruebatecnica;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API de Autenticación",
        version = "1.0",
        description = "Sistema completo de autenticación con registro, confirmación email y JWT",
        contact = @Contact(
            name = "Tu Nombre",
            email = "tu@email.com"
        ),
        license = @License(
            name = "Licencia",
            url = "https://opensource.org/licenses/MIT"
        )
    )
)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}