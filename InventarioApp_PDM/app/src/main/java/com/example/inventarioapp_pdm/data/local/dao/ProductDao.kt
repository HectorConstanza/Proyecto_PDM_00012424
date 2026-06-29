package com.example.inventarioapp_pdm.data.local.dao

import androidx.room.*
import com.example.inventarioapp_pdm.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

// Aquí están los "pedidos" que le podemos hacer a la base de datos sobre los productos
@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)
}
