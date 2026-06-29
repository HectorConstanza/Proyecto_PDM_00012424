package com.example.inventarioapp_pdm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.inventarioapp_pdm.domain.model.Product

// Esta es la tabla donde guardamos toda la mercadería en el cel
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String,
    val presentation: String,
    val stock: Int,
    val unit: String,
    val purchasePrice: Double,
    val salePrice: Double,
    val status: String,
    val imageUrl: String
)

// Estas funciones son para que la base de datos y la UI se entiendan
// toModel pasa de la tabla al objeto de la app, y toEntity al revés

fun ProductEntity.toModel(): Product {
    return Product(
        id = id.toString(),
        name = name,
        category = category,
        presentation = presentation,
        stock = stock,
        unit = unit,
        purchasePrice = purchasePrice,
        salePrice = salePrice,
        status = status,
        imageUrl = imageUrl
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = if (id.isEmpty()) 0 else id.toInt(),
        name = name,
        category = category,
        presentation = presentation,
        stock = stock,
        unit = unit,
        purchasePrice = purchasePrice,
        salePrice = salePrice,
        status = status,
        imageUrl = imageUrl
    )
}
