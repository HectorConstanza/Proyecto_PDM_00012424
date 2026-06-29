package com.example.inventarioapp_pdm.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventarioapp_pdm.components.QuickActionsSection
import com.example.inventarioapp_pdm.domain.model.Product
import com.example.inventarioapp_pdm.screen.dashboard.components.LowStockSection
import com.example.inventarioapp_pdm.screen.dashboard.components.StatsSection
import com.example.inventarioapp_pdm.ui.theme.Background
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardViewModel(
    totalProducts: Int,
    lowStockCount: Int,
    todayDispatches: Int,
    lowStockList: List<Product>,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryGreen)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // El encabezado verde con el saludo y avisos
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 24.dp, end = 24.dp, bottom = 40.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // La campana de avisos para saber si algo anda mal
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text("3")
                            }
                        },
                        modifier = Modifier.clickable { onNavigate("notifications") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "¡Hola, Admin!",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Aquí tienes el resumen de tu negocio",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 15.sp
                )
            }

            // El fondo blanco donde vive el resto de la info
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Background,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp)
                ) {
                    // Dejamos este espacio para que las tarjetas de arriba no tapen el contenido
                    Spacer(modifier = Modifier.height(140.dp))

                    QuickActionsSection(onNavigate = onNavigate)

                    Spacer(modifier = Modifier.height(40.dp))

                    LowStockSection(
                        products = lowStockList,
                        onSeeAllClick = { onNavigate("inventory") }
                    )
                    
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }

        // Las tarjetas de resumen (estas flotan un poco)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = 190.dp) 
        ) {
            StatsSection(
                totalProducts = totalProducts,
                lowStockCount = lowStockCount,
                todayDispatches = todayDispatches
            )
        }
    }
}
