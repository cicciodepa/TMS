package it.cicciodepa.projects.server.ticketing.messages

import it.cicciodepa.projects.server.ticketing.attachment.Attachment
import it.cicciodepa.projects.server.ticketing.chat.Chat
import it.cicciodepa.projects.server.ticketing.utility.Role
import java.util.*

data class MessageDTO(
    val id: Long?,
    val chat: Chat?,
    val attachments: MutableSet<Attachment>,
    val sentBy: Role?,
    val content: String,
    val sendingDate: Date?
)

fun Message.toDTO() : MessageDTO {
    return MessageDTO(id, chat, attachments, sentBy, content, sendingDate)
}

fun MessageDTO.toEntity() : Message {
    return Message(id, chat, attachments, sentBy, content, sendingDate)
}