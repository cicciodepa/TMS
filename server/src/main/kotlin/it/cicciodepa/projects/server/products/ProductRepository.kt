package it.cicciodepa.projects.server.products

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>{
}