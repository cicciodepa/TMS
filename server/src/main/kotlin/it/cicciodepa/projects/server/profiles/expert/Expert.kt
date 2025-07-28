package it.cicciodepa.projects.server.profiles.expert

import it.cicciodepa.projects.server.profiles.Profile
import it.cicciodepa.projects.server.ticketing.tickets.Ticket
import it.cicciodepa.projects.server.ticketing.utility.Role
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
data class Expert (
    override var id: String?,
    var fields: String = "",
    @Transient
    override var password: String = "",
    override var username: String,
    override var firstName: String,
    override var lastName: String,
    override var email: String,

    @OneToMany(mappedBy = "expert")
    var tickets: MutableSet<Ticket> = mutableSetOf()
) : Profile(id = id,  username = username, firstName = firstName, lastName = lastName, email = email, password = password)