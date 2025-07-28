package it.cicciodepa.projects.server.security.login

import it.cicciodepa.projects.server.security.utils.IntrospectResponse
import it.cicciodepa.projects.server.security.utils.Response
import it.cicciodepa.projects.server.security.utils.TokenRequest
import org.springframework.http.ResponseEntity

interface LoginService {
    fun login(loginRequest: LoginRequest): ResponseEntity<LoginResponse>
    fun logout(tokenRequest: TokenRequest): ResponseEntity<Response>
    fun introspect(tokenRequest: TokenRequest): ResponseEntity<IntrospectResponse>
}