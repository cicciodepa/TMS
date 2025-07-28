package it.cicciodepa.projects.server.security

import it.cicciodepa.projects.server.security.jwt.JwtAuthConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class WebSecurityConfig(
    @Autowired
    private val jwtAuthConverter: JwtAuthConverter
) {
    companion object {
        const val ADMIN = "admin"
        const val MANAGER = "manager"
        const val EXPERT = "expert"
        const val CUSTOMER = "customer"
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    // Public endpoints
                    .requestMatchers(HttpMethod.POST, "/customer/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/product**", "/product/**", "/ticket/**").permitAll()

                    // Customer role
                    .requestMatchers(HttpMethod.POST, "/ticket").hasRole(CUSTOMER)

                    // Manager & Admin roles
                    .requestMatchers(HttpMethod.POST, "/product**").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers(HttpMethod.PUT, "/product**", "/product/**").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers(HttpMethod.PATCH, "/product**").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers(HttpMethod.PUT, "/expert/**").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers("/manager/**").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers("/ticket/**").hasAnyRole(ADMIN, MANAGER) // Note: This is broad, consider refining
                    .requestMatchers(HttpMethod.PUT, "/ticket/{id}/expert").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers(HttpMethod.PUT, "/ticket/{id}/priority").hasAnyRole(ADMIN, MANAGER)
                    .requestMatchers(HttpMethod.POST, "/expert/**").hasRole(MANAGER)


                    // Expert, Manager & Admin roles
                    .requestMatchers(HttpMethod.GET, "/expert/**").hasAnyRole(ADMIN, MANAGER, EXPERT)
                    .requestMatchers("/ticket/status**").hasAnyRole(ADMIN, MANAGER, EXPERT)
                    .requestMatchers("/ticket/product/**").hasAnyRole(ADMIN, MANAGER, EXPERT)
                    .requestMatchers("/ticket/customer/**").hasAnyRole(ADMIN, MANAGER, EXPERT)

                    // All authenticated users
                    .requestMatchers("/customer/**").hasAnyRole(ADMIN, MANAGER, EXPERT, CUSTOMER)
                    .requestMatchers(HttpMethod.GET, "/ticket/status**").hasAnyRole(ADMIN, MANAGER, EXPERT, CUSTOMER)
                    .requestMatchers("/attachment**", "/chat**").hasAnyRole(ADMIN, MANAGER, EXPERT, CUSTOMER)

                    // Any other request must be authenticated
                    .anyRequest().authenticated()
            }

        http.oauth2ResourceServer { oauth2 ->
            oauth2.jwt { jwt ->
                jwt.jwtAuthenticationConverter(jwtAuthConverter)
            }
        }

        http.sessionManagement { session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        return http.build()
    }
}
