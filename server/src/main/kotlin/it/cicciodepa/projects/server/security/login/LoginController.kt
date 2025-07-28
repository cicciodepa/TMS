package it.cicciodepa.projects.server.security.login

import it.cicciodepa.projects.server.security.*
import it.cicciodepa.projects.server.security.utils.IntrospectResponse
import it.cicciodepa.projects.server.security.utils.Response
import it.cicciodepa.projects.server.security.utils.TokenRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class LoginController (
    private val loginServiceImpl: LoginServiceImpl
){

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse>{
        return loginServiceImpl.login(loginRequest)
    }

    @PostMapping("/logout")
    fun logout(@RequestBody tokenRequest: TokenRequest): ResponseEntity<Response>{
        return loginServiceImpl.logout(tokenRequest)
    }

    @PostMapping("/introspect")
    fun introspect(@RequestBody tokenRequest: TokenRequest): ResponseEntity<IntrospectResponse>{
        return loginServiceImpl.introspect(tokenRequest)
    }
}