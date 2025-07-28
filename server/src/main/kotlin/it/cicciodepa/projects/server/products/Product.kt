package it.cicciodepa.projects.server.products

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Product (
    @Id
    var ean: Long,

    var name: String = "",
    var brand: String = "",
    var category: String = "",

    var price: Double = 0.0,
)