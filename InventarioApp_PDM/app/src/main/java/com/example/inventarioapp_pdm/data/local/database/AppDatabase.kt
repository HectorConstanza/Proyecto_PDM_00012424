package com.example.inventarioapp_pdm.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventarioapp_pdm.data.local.dao.ProductDao
import com.example.inventarioapp_pdm.data.local.dao.DispatchDao
import com.example.inventarioapp_pdm.data.local.dao.CategoryDao
import com.example.inventarioapp_pdm.data.local.entity.ProductEntity
import com.example.inventarioapp_pdm.data.local.entity.DispatchEntity
import com.example.inventarioapp_pdm.data.local.entity.CategoryEntity

// Configuración de la base de datos de Room
@Database(
    entities = [ProductEntity::class, DispatchEntity::class, CategoryEntity::class], 
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun dispatchDao(): DispatchDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inventario_db",
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
