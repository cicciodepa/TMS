package it.cicciodepa.projects.server.profiles.manager

import it.cicciodepa.projects.server.profiles.expert.Expert
import it.cicciodepa.projects.server.profiles.expert.ExpertDTO

data class ManagerDTO(
    val id: String?,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val department: String
)

fun Manager.toDTO() : ManagerDTO {
    return ManagerDTO(id, username, firstName, lastName, email, password, department)
}

fun ManagerDTO.toEntity(): Manager {
    return Manager(id = id, username = username,
        firstName = firstName, lastName = lastName,
        email = email,
        password = password,
        department = department)}
