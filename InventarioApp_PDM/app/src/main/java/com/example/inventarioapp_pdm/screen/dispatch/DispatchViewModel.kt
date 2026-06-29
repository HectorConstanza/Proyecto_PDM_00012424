package com.example.inventarioapp_pdm.screen.dispatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventarioapp_pdm.domain.model.Product
import com.example.inventarioapp_pdm.ui.theme.Background
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispatchViewModel(
    products: List<Product>,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onConfirm: (Product, Int) -> Unit = { _, _ -> }
) {
    // Aquí guardamos lo que el usuario va eligiendo en el formulario
    var nombreProductoSeleccionado by remember { 
        mutableStateOf(products.firstOrNull()?.name ?: "") 
    }
    val productoActual = products.find { it.name == nombreProductoSeleccionado }

    var presentacion by remember { 
        mutableStateOf(productoActual?.presentation ?: "Caja") 
    }
    var cantidad by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    // Control para que los menús se abran y cierren
    var expandirProductos by remember { mutableStateOf(false) }
    var expandirPresentacion by remember { mutableStateOf(false) }

    val listaPresentaciones = listOf("Caja", "Unidad", "Set")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
    ) {
        // La barrita de arriba para regresar al dashboard
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.Black)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Nuevo despacho",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(48.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Selector de Producto (Menú desplegable pro)
            Text(text = "Producto", fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expandirProductos,
                onExpandedChange = { expandirProductos = !expandirProductos }
            ) {
                OutlinedTextField(
                    value = nombreProductoSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirProductos) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = PrimaryGreen
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandirProductos,
                    onDismissRequest = { expandirProductos = false }
                ) {
                    products.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion.name) },
                            onClick = {
                                nombreProductoSeleccionado = opcion.name
                                expandirProductos = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de Presentación
            Text(text = "Presentación", fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expandirPresentacion,
                onExpandedChange = { expandirPresentacion = !expandirPresentacion }
            ) {
                OutlinedTextField(
                    value = presentacion,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirPresentacion) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = PrimaryGreen
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandirPresentacion,
                    onDismissRequest = { expandirPresentacion = false }
                ) {
                    listaPresentaciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                presentacion = opcion
                                expandirPresentacion = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cuadrito para mostrar el stock que queda en bodega
            Surface(
                color = PrimaryGreen.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Stock disponible", color = PrimaryGreen, fontSize = 13.sp)
                    Text(
                        text = "${productoActual?.stock ?: 0} ${productoActual?.unit ?: "unidades"}", 
                        color = PrimaryGreen, 
                        fontWeight = FontWeight.Bold, 
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input para poner cuántos se van a llevar
            Text(text = "Cantidad a despachar", fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                placeholder = { Text("Ej: 5", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = PrimaryGreen
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Notas extras por si acaso
            Text(text = "Observaciones (opcional)", fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = notas,
                onValueChange = { notas = it },
                placeholder = { Text("Escribe aquí alguna nota...", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = PrimaryGreen
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // El botón de acción para confirmar la salida de mercadería
            Button(
                onClick = {
                    val cantInt = cantidad.toIntOrNull() ?: 0
                    if (productoActual != null && cantInt > 0) {
                        onConfirm(productoActual, cantInt)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text(text = "Confirmar despacho", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }
            
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
