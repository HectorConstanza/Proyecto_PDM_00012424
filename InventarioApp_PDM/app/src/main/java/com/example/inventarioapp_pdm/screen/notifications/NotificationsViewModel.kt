package com.example.inventarioapp_pdm.screen.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventarioapp_pdm.ui.theme.Background
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

// Clase para guardar los avisos que nos llegan
data class NotificationItem(
    val title: String,
    val message: String,
    val time: String,
    val type: NotificationType
)

// Tipos de aviso: Rojo para stock bajo, azul para lo demás
enum class NotificationType {
    STOCK_LOW, INFO
}

@Composable
fun NotificationsViewModel(
    modifier: Modifier = Modifier
) {
    // Listita de avisos para que se vea algo en la pantalla
    val notifications = listOf(
        NotificationItem(
            "Stock bajo",
            "El producto 'Ventilador 18\"' tiene stock bajo (5 unidades).",
            "Hoy, 9:00 a.m.",
            NotificationType.STOCK_LOW
        ),
        NotificationItem(
            "Stock bajo",
            "El producto 'Extractor de Aire' tiene stock bajo (3 unidades).",
            "Hoy, 8:15 a.m.",
            NotificationType.STOCK_LOW
        ),
        NotificationItem(
            "Despacho realizado",
            "Se realizó un despacho de 5 unidades de Ventilador 16\".",
            "Hoy, 7:45 a.m.",
            NotificationType.INFO
        ),
        NotificationItem(
            "Producto agregado",
            "Se agregó el producto 'Rejilla Metálica'.",
            "Ayer, 6:30 p.m.",
            NotificationType.INFO
        )
    )

    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
    ) {
        // La barrita de arriba con el nombre de la pantalla
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Notificaciones",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Las pestañas para filtrar los avisos (Todas vs No leídas)
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = PrimaryGreen,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = PrimaryGreen
                )
            },
            divider = {}
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Todas", fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("No leídas (3)", fontWeight = FontWeight.Bold) }
            )
        }

        // Aquí tiramos la lista de avisos uno por uno
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notifications) { item ->
                NotificationCard(item)
            }
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem) {
    val backgroundColor = if (item.type == NotificationType.STOCK_LOW) {
        Color(0xFFFFEBEE) // Rojito suave para alertar
    } else {
        Color(0xFFE3F2FD) // Azulito para información general
    }

    val icon = if (item.type == NotificationType.STOCK_LOW) {
        Icons.Default.Warning
    } else {
        Icons.Default.Info
    }

    val iconTint = if (item.type == NotificationType.STOCK_LOW) {
        Color(0xFFD32F2F)
    } else {
        Color(0xFF1976D2)
    }

    // El cuadrito de cada notificación
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icono chiquito a la izquierda
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Título y hora en la misma fila para que se vea pro
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = iconTint
                    )
                    Text(
                        text = item.time,
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // El chisme completo de la notificación
                Text(
                    text = item.message,
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
