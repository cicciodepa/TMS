package it.cicciodepa.projects.server.ticketing.statusTicket


import it.cicciodepa.projects.server.*
import it.cicciodepa.projects.server.ticketing.utility.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@Service
class StatusTicketServiceImpl(
    @Autowired
    private val statusTicketRepository: StatusTicketRepository
) : StatusTicketService {


    override fun getStatusTicket(id: Long): List<StatusTicketDTO>? {
        val q = statusTicketRepository?.existsByTicket(id)
        val q2 = q?:false
        if( q2 ){
            if (statusTicketRepository.existsByTicketId(id)) {
                return statusTicketRepository.findByTicketId(id)?.map { it.toDTO() }
            } else {
                throw StatusTicketUndefinedException("Ticket with id $id not opened!")
            }
        }else {
            throw TicketNotFoundException("Ticket with id $id not found!")
        }
    }

    override fun getLatestStatus(id: Long): StatusTicketDTO? {
        if(statusTicketRepository.existsByTicket(id)){
            if (statusTicketRepository.existsByTicketId(id)) {
                return statusTicketRepository.getLatestStatus(id)?.toDTO()
            } else {
                throw StatusTicketUndefinedException("Ticket with id $id not opened!")
            }
        }else {
            throw TicketNotFoundException("Ticket with id $id not found!")
        }
    }

    override fun openStatusTicket(statusTicket: StatusTicket) {
        if (statusTicket.ticketDate != null && statusTicketRepository.existsById(statusTicket.ticketDate!!)) {
            throw StatusTicketAlreadyInsertedException("Status Ticket with id ${statusTicket.ticketDate} already exists")
        } else {
            val ticket = statusTicket.ticketDate?.ticket!!
            if (statusTicketRepository.existsByTicket(ticket.id!!)) {
                if (statusTicketRepository.existsByTicketId(ticket.id!!)) {
                    val status = getLatestStatus(ticket.id!!)?.status
                    if (status?.equals(Status.IN_PROGRESS)!!
                        || status.equals(null)
                    ) {
                        statusTicket.status = Status.OPEN
                        statusTicket.ticketDate?.lastModifiedDate = Instant.now() as Date
                        statusTicketRepository.save(statusTicket)
                    } else {
                        throw StatusTicketAlreadyOpenedException("Status ticket with id ${statusTicket.ticketDate} already opened")
                    }
                    statusTicket.status = Status.OPEN
                    statusTicket.ticketDate?.lastModifiedDate = Instant.now() as Date
                    statusTicketRepository.save(statusTicket)
                }else {
                    statusTicket.status = Status.OPEN
                    statusTicket.ticketDate?.lastModifiedDate = SimpleDateFormat("YYYY-MM-DD").parse(Instant.now().toString())
                    statusTicketRepository.save(statusTicket)
                }
            }else{
                throw TicketNotFoundException("Ticket with id ${statusTicket.ticketDate?.ticket?.id} not found!")
            }
        }
    }

    override fun closeStatusTicket(statusTicket: StatusTicket) {
        if (statusTicket.ticketDate != null && statusTicketRepository.existsById(statusTicket.ticketDate!!)) {
            throw StatusTicketAlreadyInsertedException("Status Ticket with id ${statusTicket.ticketDate} already exists")
        }
        val ticket = statusTicket.ticketDate?.ticket!!
        if (statusTicketRepository.existsByTicket(ticket.id!!)) {
            if (statusTicketRepository.existsByTicketId(ticket.id!!)) {
                val status = getLatestStatus(ticket.id!!)?.status
                if(status == Status.CLOSED) {
                    throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} already opened or in progress.")
                }else {
                    statusTicket.status = Status.CLOSED
                    statusTicketRepository.save(statusTicket)
                }
            }else {
                throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} not opened")
            }
        }else {
            throw TicketNotFoundException("Ticket with id ${statusTicket.ticketDate?.ticket?.id} not found!")
        }
    }

    override fun reopenStatusTicket(statusTicket: StatusTicket) {
        if (statusTicket.ticketDate != null && statusTicketRepository.existsById(statusTicket.ticketDate!!)) {
            throw StatusTicketAlreadyInsertedException("Status Ticket with id ${statusTicket.ticketDate} already exists")
        }
        val ticket = statusTicket.ticketDate?.ticket!!
        if (statusTicketRepository.existsByTicket(ticket.id!!)) {
            if (statusTicketRepository.existsByTicketId(ticket.id!!)) {
                val status = getLatestStatus(ticket.id!!)?.status
                if(status == Status.OPEN
                    || status == Status.IN_PROGRESS
                    || status == Status.REOPENED
                ) {
                    throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} already opened or in progress.")
                }else {
                    statusTicket.status = Status.REOPENED
                    statusTicketRepository.save(statusTicket)
                }
            }else {
                throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} not opened")
            }
        }else {
            throw TicketNotFoundException("Ticket with id ${statusTicket.ticketDate?.ticket?.id} not found!")
        }
    }

    override fun resolveStatusTicket(statusTicket: StatusTicket) {
        if (statusTicket.ticketDate != null && statusTicketRepository.existsById(statusTicket.ticketDate!!)) {
            throw StatusTicketAlreadyInsertedException("Status Ticket with id ${statusTicket.ticketDate} already exists")
        }
        val ticket = statusTicket.ticketDate?.ticket!!
        if (statusTicketRepository.existsByTicket(ticket.id!!)) {
            if (statusTicketRepository.existsByTicketId(ticket.id!!)) {
                val status = getLatestStatus(ticket.id!!)?.status
                if(status == Status.CLOSED
                    || status == Status.RESOLVED
                ) {
                    throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} already closed")
                }else {
                    statusTicket.status = Status.RESOLVED
                    statusTicketRepository.save(statusTicket)
                }
            }else {
                throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} not opened")
            }
        }else{
        throw TicketNotFoundException("Ticket with id ${statusTicket.ticketDate?.ticket?.id} not found!")}
    }

    override fun progressStatusTicket(statusTicket: StatusTicket) {
        if (statusTicket.ticketDate != null && statusTicketRepository.existsById(statusTicket.ticketDate!!)) {
            throw StatusTicketAlreadyInsertedException("Status Ticket with id ${statusTicket.ticketDate} already exists")
        }
        val ticket = statusTicket.ticketDate?.ticket!!
        if (statusTicketRepository.existsByTicket(ticket.id!!)) {
            if (statusTicketRepository.existsByTicketId(ticket.id!!)) {
                val status = getLatestStatus(ticket.id!!)?.status
                if(status == Status.CLOSED
                    || status == Status.RESOLVED
                ) {
                    throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} already closed")
                }else if(status == Status.IN_PROGRESS) {
                    throw StatusTicketAlreadyOpenedException("Status ticket with id ${statusTicket.ticketDate} already in progress")
                }
                else {
                    statusTicket.status = Status.IN_PROGRESS
                    statusTicketRepository.save(statusTicket)
                }
            }else {
                throw StatusTicketAlreadyClosedException("Status ticket with id ${statusTicket.ticketDate} not opened")
            }
        }else {
            throw TicketNotFoundException("Ticket with id ${statusTicket.ticketDate?.ticket?.id} not found!")
        }
    }

}





