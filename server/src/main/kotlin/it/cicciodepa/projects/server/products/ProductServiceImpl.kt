package it.cicciodepa.projects.server.products

import it.cicciodepa.projects.server.DuplicateProductException
import it.cicciodepa.projects.server.ProductNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.text.category
import kotlin.text.get

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
): ProductService {
    override fun getAll(): List<ProductDTO> {
        return productRepository.findAll().map { it.toDTO() }
    }

    override fun getProduct(ean: Long): ProductDTO? {
        return productRepository.findByIdOrNull(ean)?.toDTO()
    }

    override fun insertProduct(product: Product) {
        if (productRepository.existsById(product.ean)) {
            throw DuplicateProductException("Product with ean ${product.ean} already inserted!")
        }
        else {
            productRepository.save(product)
        }
    }

    override fun updateProduct(product: Product) {
        if (productRepository.existsById(product.ean)) {
            val retrievedProduct = productRepository.findById(product.ean).get()

            retrievedProduct.name = product.name
            retrievedProduct.brand = product.brand
            retrievedProduct.category = product.category
            retrievedProduct.price = product.price

            productRepository.save(retrievedProduct)
        }
        else {
            throw ProductNotFoundException("Product with ean ${product.ean} not found")
        }
    }

}