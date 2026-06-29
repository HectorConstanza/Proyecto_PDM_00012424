package com.example.inventarioapp_pdm.data.repository

import com.example.inventarioapp_pdm.data.local.dao.ProductDao
import com.example.inventarioapp_pdm.data.local.entity.toModel
import com.example.inventarioapp_pdm.data.local.entity.toEntity
import com.example.inventarioapp_pdm.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Este compa es el intermediario, se encarga de que los datos lleguen limpios a la UI
class ProductRepository(private val productDao: ProductDao) {

    // Traemos todo el inventario y lo convertimos para que la UI lo entienda
    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts().map { entities ->
            entities.map { it.toModel() }
        }
    }

    suspend fun getProductById(id: Int): Product? {
        return productDao.getProductById(id)?.toModel()
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product.toEntity())
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product.toEntity())
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product.toEntity())
    }
}
