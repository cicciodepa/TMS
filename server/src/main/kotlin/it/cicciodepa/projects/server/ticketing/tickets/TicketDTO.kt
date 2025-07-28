package it.cicciodepa.projects.server.ticketing.tickets

import it.cicciodepa.projects.server.products.Product
import it.cicciodepa.projects.server.products.ProductDTO
import it.cicciodepa.projects.server.products.toDTO
import it.cicciodepa.projects.server.products.toEntity
import it.cicciodepa.projects.server.ticketing.chat.Chat
import it.cicciodepa.projects.server.profiles.customer.Customer
import it.cicciodepa.projects.server.profiles.customer.CustomerDTO
import it.cicciodepa.projects.server.profiles.customer.toDTO
import it.cicciodepa.projects.server.profiles.customer.toEntity
import it.cicciodepa.projects.server.profiles.expert.Expert
import it.cicciodepa.projects.server.profiles.expert.ExpertDTO
import it.cicciodepa.projects.server.profiles.expert.toDTO
import it.cicciodepa.projects.server.profiles.expert.toEntity
import it.cicciodepa.projects.server.ticketing.statusTicket.StatusTicket
import it.cicciodepa.projects.server.ticketing.statusTicket.TicketDate
import it.cicciodepa.projects.server.ticketing.utility.Status
import java.time.Instant
import java.util.Date

data class TicketDTO(
    val id: Long?,
    val customer: CustomerDTO?,
    val expert: ExpertDTO?,
    val product: ProductDTO?,
    val issueType: String,
    val description: String,
    val priorityLevel: Int?,
    val dateOfCreation: Date?
)

fun Ticket.toDTO() : TicketDTO {
    return TicketDTO(id, customer?.toDTO(), expert?.toDTO(), product?.toDTO(), issueType, description, priorityLevel, dateOfCreation)
}

fun TicketDTO.toEntity(): Ticket {
    val ticket = Ticket(customer =  customer?.toEntity(),
        expert = expert?.toEntity(),
        product = product?.toEntity(),
        issueType = issueType,
        description = description,
        priorityLevel = priorityLevel,
        dateOfCreation = dateOfCreation
    )
    ticket.id = id
    return ticket
}