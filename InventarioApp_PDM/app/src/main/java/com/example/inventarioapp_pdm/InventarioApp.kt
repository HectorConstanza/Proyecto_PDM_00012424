package com.example.inventarioapp_pdm

import android.app.Application
import com.example.inventarioapp_pdm.data.local.database.AppDatabase
import com.example.inventarioapp_pdm.data.repository.ProductRepository
import com.example.inventarioapp_pdm.data.repository.DispatchRepository
import com.example.inventarioapp_pdm.data.repository.CategoryRepository

// Esta es la clase principal de la app, se corre antes que todo
class InventarioApp : Application() {
    
    // Aquí inicializamos la base de datos y los repositorios
    val database by lazy { AppDatabase.getDatabase(this) }
    val productRepository by lazy { ProductRepository(database.productDao()) }
    val dispatchRepository by lazy { DispatchRepository(database.dispatchDao()) }
    val categoryRepository by lazy { CategoryRepository(database.categoryDao()) }
}
