package it.cicciodepa.projects.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class TicketingApplication

fun main(args: Array<String>) {
	runApplication<TicketingApplication>(*args)
}
