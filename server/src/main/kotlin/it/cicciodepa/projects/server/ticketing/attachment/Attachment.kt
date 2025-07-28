package it.cicciodepa.projects.server.ticketing.attachment

import it.cicciodepa.projects.server.ticketing.messages.Message
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class Attachment(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    var message: Message? = null,

    var name: String = "",
    var content: String = "",
    var size: Int? = null
)