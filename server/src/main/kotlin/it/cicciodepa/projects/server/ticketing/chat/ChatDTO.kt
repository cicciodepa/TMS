package it.cicciodepa.projects.server.ticketing.chat

import it.cicciodepa.projects.server.ticketing.messages.Message
import it.cicciodepa.projects.server.ticketing.tickets.Ticket
import java.util.*

data class ChatDTO(
    val id: Long?,
    val messages: MutableSet<Message>,
    val ticket: Ticket?,
    val creationDate: Date?
)

fun Chat.toDTO() : ChatDTO {
    return ChatDTO(id, messages, ticket, creationDate)
}
fun ChatDTO.toEntity() : Chat {
    return Chat(id, messages, ticket, creationDate)
}