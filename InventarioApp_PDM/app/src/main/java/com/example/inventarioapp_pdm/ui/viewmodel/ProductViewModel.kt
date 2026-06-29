package com.example.inventarioapp_pdm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventarioapp_pdm.data.local.entity.DispatchEntity
import com.example.inventarioapp_pdm.data.repository.CategoryRepository
import com.example.inventarioapp_pdm.data.repository.DispatchRepository
import com.example.inventarioapp_pdm.data.repository.ProductRepository
import com.example.inventarioapp_pdm.domain.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Este es el cerebro que conecta la base de datos con todas las pantallas de la app
class ProductViewModel(
    private val productRepository: ProductRepository,
    private val dispatchRepository: DispatchRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    // Aquí "escuchamos" el inventario en tiempo real desde Room
    val allProducts: StateFlow<List<Product>> = productRepository.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Traemos las categorías que el usuario ya creó
    val categories: StateFlow<List<String>> = categoryRepository.getAllCategoryNames()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Sumamos cuántas cosas se han despachado hoy para el dashboard
    val todayDispatchesCount: StateFlow<Int> = dispatchRepository.getTodayDispatchesCount()
        .map { it.sumOf { d -> d.quantity } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    // Función para meter un producto nuevo a la base
    fun addProduct(product: Product) {
        viewModelScope.launch {
            productRepository.insertProduct(product)
        }
    }

    // Por si hay que cambiarle el nombre o algo a un producto
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            productRepository.updateProduct(product)
        }
    }

    // Aquí hacemos la magia del despacho: baja el stock y guarda el registro
    fun recordDispatch(product: Product, quantity: Int) {
        viewModelScope.launch {
            // 1. Descontamos lo que salió de la bodega
            val updatedProduct = product.copy(stock = product.stock - quantity)
            productRepository.updateProduct(updatedProduct)

            // 2. Guardamos el movimiento en la tabla de despachos
            val dispatch = DispatchEntity(
                productId = product.id.toIntOrNull() ?: 0,
                productName = product.name,
                quantity = quantity
            )
            dispatchRepository.insertDispatch(dispatch)
        }
    }

    // Esta fábrica es necesaria para que Android pueda inyectar los repositorios al ViewModel
    class Factory(
        private val productRepo: ProductRepository,
        private val dispatchRepo: DispatchRepository,
        private val categoryRepo: CategoryRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductViewModel(productRepo, dispatchRepo, categoryRepo) as T
            }
            throw IllegalArgumentException("Ni idea de qué clase es este ViewModel")
        }
    }
}
