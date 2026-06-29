package com.example.inventarioapp_pdm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)

// Mappers al estilo del profe
fun CategoryEntity.toModel(): String = name
