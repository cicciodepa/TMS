package it.cicciodepa.projects.server.security.utils

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableWebSecurity
class KeycloakConfig {
    @Bean
    fun keycloakConfigResolver(): KeycloakConfigResolver {
        return KeycloakSpringBootConfigResolver()
    }

}