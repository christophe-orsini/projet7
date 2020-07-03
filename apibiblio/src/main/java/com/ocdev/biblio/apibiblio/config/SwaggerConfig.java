package com.ocdev.biblio.apibiblio.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.ocdev.biblio.apibiblio.controllers"))
                .build().
                apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "REST API des bibliothèques de Bouquiville", //title
                "Mise à disposition d'endpoints pour la gestion des prêts des bibliothèques", //description
                "Version 1.0", //version
                "Conditions d'utilisation", //terms of service URL
                new Contact("OCDev", "www.ocdev.com", "info@ocdev.com"),
                "Licenc de l'API", "API license URL", Collections.emptyList()); // contact info
    }
}