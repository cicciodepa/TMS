package it.cicciodepa.projects.server.profiles.customer

import it.cicciodepa.projects.server.ticketing.tickets.TicketDTO

interface CustomerService {
    fun getCustomer(email: String) : CustomerDTO?
    fun customerSignup(customer:Customer)

    fun updateCustomer(customer: Customer)

    fun getTickets(id: String) : Set<TicketDTO>?
}