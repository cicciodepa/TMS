package it.cicciodepa.projects.server.ticketing.messages

import it.cicciodepa.projects.server.ticketing.attachment.AttachmentDTO

interface MessageService {
    fun getMessage(id: Long) : MessageDTO?

    fun getMessageByChat(chatId: Long) : List<MessageDTO>?

    fun getAttachments(id: Long) : Set<AttachmentDTO>?

    fun insertMessage(message: Message)
}