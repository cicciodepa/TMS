package it.cicciodepa.projects.server.ticketing.chat

import it.cicciodepa.projects.server.ticketing.messages.MessageDTO
import java.util.Date

interface ChatService {
    fun getChat(id: Long) : ChatDTO?

    fun getChatByTicket(ticketId: Long) : List<ChatDTO>?

    fun getChatByDate(date: Date) : List<ChatDTO>?

    fun getMessages(id: Long) : Set<MessageDTO>?

    fun insertChat(chat: Chat)
}