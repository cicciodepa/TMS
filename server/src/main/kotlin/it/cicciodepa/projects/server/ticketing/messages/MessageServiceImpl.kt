package it.cicciodepa.projects.server.ticketing.messages

import it.cicciodepa.projects.server.MessageAlreadySentException
import it.cicciodepa.projects.server.MessageNotFoundException
import it.cicciodepa.projects.server.ticketing.attachment.AttachmentDTO
import it.cicciodepa.projects.server.ticketing.attachment.toDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository
) : MessageService {
    override fun getMessage(id: Long): MessageDTO? {
        return messageRepository.findByIdOrNull(id)?.toDTO()
    }

    override fun getMessageByChat(chatId: Long): List<MessageDTO>? {
        return messageRepository.findByChat(chatId)?.map { it.toDTO() }
    }

    override fun getAttachments(id: Long): Set<AttachmentDTO>? {
        if (messageRepository.existsById(id)) {
            val attachments = messageRepository.getAttachments(id) ?: return null
            return attachments.map { it.toDTO() }.toSet()
        }
        else {
            throw MessageNotFoundException("Message with id $id not found!")
        }
    }

    override fun insertMessage(message: Message) {
        if (message.id != null && messageRepository.existsById(message.id!!)) {
            throw MessageAlreadySentException("Message with id ${message.id} already inserted!")
        }
        else {
            messageRepository.save(message)
        }
    }
}


