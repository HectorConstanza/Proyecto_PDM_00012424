package com.example.inventarioapp_pdm.screen.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventarioapp_pdm.domain.model.Product
import com.example.inventarioapp_pdm.ui.theme.Background
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailViewModel(
    product: Product,
    onBack: () -> Unit = {},
    onEdit: () -> Unit = {},
    onDispatch: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
    ) {
        // La barrita de arriba con el botón de regreso
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
            }
            Text(
                text = "Detalle del producto",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = {  }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // El cuadrito donde va la foto del producto
            Surface(
                modifier = Modifier
                    .size(200.dp),
                color = Color.White,
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 2.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Photo", fontSize = 60.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Nombre y el estado (si está activo o no)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                Surface(
                    color = PrimaryGreen.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = product.status,
                        color = PrimaryGreen,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjetitas de info (Categoría y Presentación)
            Row(modifier = Modifier.fillMaxWidth()) {
                InfoColumn(label = "Categoría", value = product.category, modifier = Modifier.weight(1f))
                InfoColumn(label = "Presentación", value = product.presentation, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // El cuadro blanco con el stock disponible
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Stock disponible", color = Color.Gray, fontSize = 14.sp)
                    Text(
                        text = "${product.stock} ${product.unit}",
                        color = PrimaryGreen,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Precios de compra y venta
            Row(modifier = Modifier.fillMaxWidth()) {
                PriceColumn(label = "Precio de compra", value = "$${product.purchasePrice}", modifier = Modifier.weight(1f))
                PriceColumn(label = "Precio de venta", value = "$${product.salePrice}", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.weight(1f))

            // Los botones de abajo para editar o despachar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onEdit,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
                ) {
                    Text("Editar", color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onDispatch,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                ) {
                    Text("Despachar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun InfoColumn(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun PriceColumn(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
