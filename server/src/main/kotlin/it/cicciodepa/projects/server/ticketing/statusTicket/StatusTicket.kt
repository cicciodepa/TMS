package it.cicciodepa.projects.server.ticketing.statusTicket

import it.cicciodepa.projects.server.ticketing.tickets.Ticket
import it.cicciodepa.projects.server.ticketing.utility.Status
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Embeddable
data class TicketDate(
    // Remove @MapsId and add @JoinColumn
    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_id")
    var ticket: Ticket? = null,

    @Temporal(TemporalType.TIMESTAMP)
    var lastModifiedDate: Date? = null
) : Serializable

@Entity
data class StatusTicket(
    @EmbeddedId
    var ticketDate: TicketDate? = null,
    @Enumerated(value = EnumType.STRING)
    var status: Status? = null
)