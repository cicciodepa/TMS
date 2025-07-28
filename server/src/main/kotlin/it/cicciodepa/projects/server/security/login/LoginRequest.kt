package it.cicciodepa.projects.server.security.login

data class LoginRequest (
    var username: String,
    var password: String
)