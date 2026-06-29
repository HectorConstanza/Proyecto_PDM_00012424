package com.example.inventarioapp_pdm.screen.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.inventarioapp_pdm.components.StatCard
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

@Composable
fun StatsSection(
    totalProducts: Int,
    lowStockCount: Int,
    todayDispatches: Int
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        StatCard(
            title = "Productos",
            value = totalProducts.toString(),
            valueColor = PrimaryGreen,
            modifier = Modifier.weight(1f),
            subtitle = "Totales",
            titleColor = PrimaryGreen
        )

        StatCard(
            title = "Stock bajo",
            value = lowStockCount.toString(),
            valueColor = Color(0xFFFF9800),
            modifier = Modifier.weight(1f),
            titleColor = Color(0xFFFF9800)
        )

        StatCard(
            title = "Despachos",
            value = todayDispatches.toString(),
            valueColor = Color(0xFF1976D2),
            modifier = Modifier.weight(1f),
            subtitle = "Hoy",
            titleColor = Color.DarkGray
        )
    }
}
