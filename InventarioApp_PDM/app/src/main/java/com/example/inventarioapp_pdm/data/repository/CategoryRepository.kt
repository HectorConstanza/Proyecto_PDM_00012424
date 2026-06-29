package com.example.inventarioapp_pdm.data.repository

import com.example.inventarioapp_pdm.data.local.dao.CategoryDao
import com.example.inventarioapp_pdm.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepository(private val categoryDao: CategoryDao) {

    // Traemos los nombres de las categorías como una lista de Strings
    fun getAllCategoryNames(): Flow<List<String>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { it.name }
        }
    }

    suspend fun addCategory(name: String) {
        categoryDao.insertCategory(CategoryEntity(name = name))
    }
}
