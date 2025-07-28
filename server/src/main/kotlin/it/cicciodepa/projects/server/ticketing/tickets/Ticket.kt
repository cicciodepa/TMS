package it.cicciodepa.projects.server.ticketing.tickets

import it.cicciodepa.projects.server.products.Product
import it.cicciodepa.projects.server.ticketing.chat.Chat
import it.cicciodepa.projects.server.profiles.customer.Customer
import it.cicciodepa.projects.server.profiles.expert.Expert
import it.cicciodepa.projects.server.ticketing.statusTicket.StatusTicket
import jakarta.persistence.*
import java.util.*

@Entity
data class Ticket(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    var customer: Customer? = null,
    @ManyToOne
    var expert: Expert? = null,
    @ManyToOne
    var product: Product? = null,

    // Add this bidirectional mapping
    @OneToMany(mappedBy = "ticketDate.ticket", cascade = [CascadeType.ALL], orphanRemoval = true)
    var statusHistory: MutableList<StatusTicket> = mutableListOf(),

    @OneToMany(mappedBy = "ticket")
    var chats: MutableSet<Chat> = mutableSetOf(),

    var issueType: String = "",
    @Column(length = 10000)
    var description: String = "",
    var priorityLevel: Int? = null,
    @Temporal(TemporalType.TIMESTAMP)
    var dateOfCreation: Date? = null
)
