package it.cicciodepa.projects.server.ticketing.statusTicket

import it.cicciodepa.projects.server.ticketing.tickets.TicketDTO
import it.cicciodepa.projects.server.ticketing.tickets.toDTO
import it.cicciodepa.projects.server.ticketing.tickets.toEntity
import it.cicciodepa.projects.server.ticketing.utility.Status
import java.util.Date

data class StatusTicketDTO(
    val tId: TicketDTO?,
    val lastModifiedDate: Date?,
    val status: Status?
)

data class TicketDateDTO(
    val id: Long?,
    val lastModifiedDate: Date?
)

fun StatusTicket.toDTO() : StatusTicketDTO {
    return StatusTicketDTO(
        tId = this.ticketDate?.ticket?.toDTO(),
        lastModifiedDate = this.ticketDate!!.lastModifiedDate!!,
        status = this.status!!
    )
}

fun StatusTicketDTO.toEntity() : StatusTicket {
    val td = TicketDate(ticket = this.tId?.toEntity(),
    lastModifiedDate = this.lastModifiedDate)
    return StatusTicket(
        td,
        status = this.status
    )
}