package it.cicciodepa.projects.server.profiles.expert

import it.cicciodepa.projects.server.ticketing.tickets.TicketDTO

interface ExpertService {
    fun getExpert(email: String) : ExpertDTO?

    fun getExpertsByField(field: String) : List<ExpertDTO>

    fun insertExpert(expert: Expert)

    fun updateExpert(expert: Expert)

    fun getTickets(id: String) : Set<TicketDTO>?
}