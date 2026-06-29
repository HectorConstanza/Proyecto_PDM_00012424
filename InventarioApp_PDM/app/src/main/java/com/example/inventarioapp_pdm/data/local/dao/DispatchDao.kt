package com.example.inventarioapp_pdm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.inventarioapp_pdm.data.local.entity.DispatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DispatchDao {
    // Para contar cuántos despachos llevamos hoy
    @Query("SELECT * FROM dispatches WHERE timestamp >= :startOfDay")
    fun getTodayDispatches(startOfDay: Long): Flow<List<DispatchEntity>>

    @Insert
    suspend fun insertDispatch(dispatch: DispatchEntity)
}
