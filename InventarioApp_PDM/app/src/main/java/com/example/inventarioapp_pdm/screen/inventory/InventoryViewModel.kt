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
fun InventoryViewModel(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onProductClick: (Product) -> Unit = {}
) {
    // datos harcordeados para que se vea llena la pantalla
    val products = listOf(
        Product(name = "Ventilador 18\"", presentation = "Caja", stock = 15, category = "Ventiladores"),
        Product(name = "Ventilador 16\"", presentation = "Unidad", stock = 32, category = "Ventiladores"),
        Product(name = "Ventilador de Mesa", presentation = "Unidad", stock = 7, category = "Ventiladores"),
        Product(name = "Extractor de Aire", presentation = "Caja", stock = 3, category = "Extractores"),
        Product(name = "Rejilla Metálica", presentation = "Caja", stock = 30, category = "Repuestos"),
        Product(name = "Ventilador Industrial", presentation = "Unidad", stock = 5, category = "Ventiladores"),
        Product(name = "Aspas de Repuesto", presentation = "Set", stock = 20, category = "Repuestos")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding() // para que la cámara no nos tape el título
    ) {

        // El encabezado con el botón de irse para atrás
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
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

        // La barra de búsqueda y el filtro
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
            
            // Botón verde transparente para filtrar
            Surface(
                modifier = Modifier.size(56.dp),
                color = PrimaryGreen.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filtrar",
                        tint = PrimaryGreen
                    )
                }
            }
        }

        // Aquí mostramos todos los productos en una lista
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onClick = { onProductClick(product) }
                )
            }
        }
    }
}
