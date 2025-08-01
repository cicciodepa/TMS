package it.cicciodepa.projects.server.profiles.customer

import it.cicciodepa.projects.server.ticketing.utility.Role

data class CustomerDTO (
    val id: String?,
    val username: String,
    val firstName: String,
    val lastName: String,
    val password: String?,
    val email: String,
    val city: String,
    val address: String
)

fun Customer.toDTO() : CustomerDTO {
    return CustomerDTO(id, username, firstName, lastName, password, email, city, address)
}

fun CustomerDTO.toEntity(): Customer {
    return Customer(
        id = id,
        username = username,
        firstName = firstName, lastName = lastName,
        password = password ?: "password", email = email,
        city = city, address = address
    )
}