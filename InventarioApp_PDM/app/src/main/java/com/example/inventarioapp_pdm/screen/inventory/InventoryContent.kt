package com.example.inventarioapp_pdm.screen.inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventarioapp_pdm.domain.model.Product
import com.example.inventarioapp_pdm.screen.inventory.components.ProductItem
import com.example.inventarioapp_pdm.ui.theme.Background
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    val products = listOf(
        Product("Ventilador 18\"", "Caja", 15, "unidades"),
        Product("Ventilador 16\"", "Unidad", 32, "unidades"),
        Product("Ventilador de Mesa", "Unidad", 7, "unidades"),
        Product("Extractor de Aire", "Caja", 3, "unidades"),
        Product("Rejilla Metálica", "Caja", 30, "unidades"),
        Product("Ventilador Industrial", "Unidad", 5, "unidades"),
        Product("Aspas de Repuesto", "Set", 20, "unidades")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding() // esto es para evitar que tope con algunos notch y que tambien sea mas responsive
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Botón Regresar
            IconButton(
                onClick = { onBack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar",
                    tint = Color.Black
                )
            }


            Text(
                text = "Inventario",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Search and Filter Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar producto...", color = Color.Gray) },
                leadingIcon = { 
                    Icon(
                        imageVector = Icons.Default.Search, 
                        contentDescription = null, 
                        tint = Color.Gray 
                    ) 
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = PrimaryGreen
                )
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Surface(
                modifier = Modifier.size(56.dp),
                color = PrimaryGreen.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            ) {
                IconButton(onClick = { /* Filter */ }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filtrar",
                        tint = PrimaryGreen
                    )
                }
            }
        }

        // Product List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(products) { product ->
                ProductItem(product = product)
            }
        }
    }
}
