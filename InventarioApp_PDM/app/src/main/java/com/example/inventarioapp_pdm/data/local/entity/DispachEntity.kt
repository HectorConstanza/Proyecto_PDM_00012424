package com.example.inventarioapp_pdm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dispatches")
data class DispatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val timestamp: Long = System.currentTimeMillis() // Para saber que fue "Hoy"
)