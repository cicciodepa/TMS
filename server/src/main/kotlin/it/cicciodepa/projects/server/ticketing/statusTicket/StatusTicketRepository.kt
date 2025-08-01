package it.cicciodepa.projects.server.ticketing.statusTicket

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StatusTicketRepository : JpaRepository<StatusTicket, TicketDate> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StatusTicket s WHERE s.ticketDate.ticket.id = :id")
    fun existsByTicketId(@Param("id") id: Long) : Boolean

    @Query("""SELECT CASE
        WHEN (SELECT COUNT(s) FROM StatusTicket s WHERE s.ticketDate.ticket.id = :id) > 0
        OR (SELECT COUNT(t) FROM Ticket t WHERE t.id = :id) > 0
        THEN true ELSE false END FROM Ticket""")
    fun existsByTicket(@Param("id") id: Long): Boolean
    @Query("SELECT s FROM StatusTicket s WHERE s.ticketDate.ticket.id = :id")
    fun findByTicketId(@Param("id") id: Long) : List<StatusTicket>?

    @Query("""
        SELECT s FROM StatusTicket s
        WHERE s.ticketDate.ticket.id = :id
        AND s.ticketDate.lastModifiedDate = (
            SELECT MAX(s2.ticketDate.lastModifiedDate)
            FROM StatusTicket s2
            WHERE s2.ticketDate.ticket.id = :id
        )
    """)
    fun getLatestStatus(@Param("id") id: Long) : StatusTicket?
}