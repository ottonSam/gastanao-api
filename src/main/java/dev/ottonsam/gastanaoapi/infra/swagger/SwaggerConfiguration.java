package dev.ottonsam.gastanaoapi.infra.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {

    @Value("${api.security.token.secret}")
    private String secret;

    @Bean
    public OpenAPI infoApi() {

        Info info = new Info();
        info.setTitle("GastaNao-API");

        SecurityRequirement securityItem = new SecurityRequirement();
        securityItem.addList(secret);

        Components components = new Components();
        components.addSecuritySchemes(secret, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));

        return new OpenAPI().components(components)
                .addSecurityItem(securityItem)
                .info(info.description("API para controle da vida financeira").version("1.0.0"));
    }

}
