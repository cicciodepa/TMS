package it.cicciodepa.projects.server.ticketing.tickets

import it.cicciodepa.projects.server.ticketing.chat.Chat
import it.cicciodepa.projects.server.profiles.expert.Expert
import it.cicciodepa.projects.server.ticketing.statusTicket.StatusTicket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface TicketRepository : JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t LEFT JOIN FETCH t.customer WHERE t.customer.id = :id")
    fun findByCustomer(@Param("id") id: Long) : List<Ticket>?

    @Query("SELECT t FROM Ticket t LEFT JOIN FETCH t.expert WHERE t.expert.id = :id")
    fun findByExpert(@Param("id") id: Long) : List<Ticket>?

    @Query("SELECT t FROM Ticket t LEFT JOIN FETCH t.product WHERE t.product.ean = :id")
    fun findByProduct(@Param("id") id: Long) : List<Ticket>?

    @Query("SELECT t FROM Ticket t WHERE DATE(t.dateOfCreation) = DATE(:dateOfCreation)")
    fun findByDateOfCreation(@Param("dateOfCreation") dateOfCreation: Date) : List<Ticket>?

//    @Query("SELECT s FROM StatusTicket s WHERE s.ticketDate.id.id = :id")
//    fun getStatusTicket(@Param("id") id: Long) : List<StatusTicket>?

//    @Query("SELECT c FROM Chat c WHERE c.ticket.id = :id")
//    fun getChats(@Param("id") id: Long) : List<Chat>?

    @Modifying
    @Query("UPDATE Ticket t SET t.priorityLevel = :priorityLevel WHERE t.id = :id")
    fun setPriorityLevel(@Param("id") id: Long, @Param("priorityLevel") priorityLevel: Int)

    @Modifying
    @Query("UPDATE Ticket t SET t.expert = :expert WHERE t.id = :id")
    fun setExpert(@Param("id") id: Long, @Param("expert") expert: Expert)
}