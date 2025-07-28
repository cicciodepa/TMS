package it.cicciodepa.projects.server.profiles.expert

import it.cicciodepa.projects.server.profiles.customer.Customer
import it.cicciodepa.projects.server.ticketing.tickets.Ticket

data class ExpertDTO(
    val id: String?,
    val username: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val fields: String
)

fun Expert.toDTO() : ExpertDTO {
    return ExpertDTO(id,  username, firstName, lastName, email, password, fields)
}

fun ExpertDTO.toEntity(): Expert {
    return Expert(id = id, username = username,
        firstName = firstName, lastName = lastName, email =  email, password = password, fields =  fields)

}