package it.cicciodepa.projects.server.ticketing.attachment

import it.cicciodepa.projects.server.AttachmentNotFoundException
import it.cicciodepa.projects.server.EmptyPostBodyException
import it.cicciodepa.projects.server.MessageNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/attachment")
class AttachmentController(
    private val attachmentService: AttachmentService
) {
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getAttachment(@PathVariable id: Long) : AttachmentDTO? {
        return attachmentService.getAttachment(id) ?: throw AttachmentNotFoundException("Attachment with id $id not found!")
    }

    @GetMapping("/message/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    fun getAttachmentByMessage(@PathVariable messageId: Long) : List<AttachmentDTO>? {
        return attachmentService.getAttachmentByMessage(messageId) ?: throw MessageNotFoundException("Message with id $messageId not found!")
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun insertAttachment(@RequestBody attachmentDTO: AttachmentDTO?) {
        if (attachmentDTO != null) {
            attachmentService.insertAttachment(attachmentDTO.toEntity())
        }
        else {
            throw EmptyPostBodyException("Empty attachment body")
        }
    }
}