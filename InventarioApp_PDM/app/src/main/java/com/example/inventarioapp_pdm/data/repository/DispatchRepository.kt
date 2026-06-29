package com.example.inventarioapp_pdm.data.repository

import com.example.inventarioapp_pdm.data.local.dao.DispatchDao
import com.example.inventarioapp_pdm.data.local.entity.DispatchEntity
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class DispatchRepository(private val dispatchDao: DispatchDao) {

    // Función para obtener cuántos despachos se han hecho hoy
    fun getTodayDispatchesCount(): Flow<List<DispatchEntity>> {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return dispatchDao.getTodayDispatches(calendar.timeInMillis)
    }

    suspend fun insertDispatch(dispatch: DispatchEntity) {
        dispatchDao.insertDispatch(dispatch)
    }
}
