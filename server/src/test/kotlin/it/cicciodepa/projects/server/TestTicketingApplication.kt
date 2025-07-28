package it.cicciodepa.projects.server

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<TicketingApplication>().with(TestcontainersConfiguration::class).run(*args)
}
