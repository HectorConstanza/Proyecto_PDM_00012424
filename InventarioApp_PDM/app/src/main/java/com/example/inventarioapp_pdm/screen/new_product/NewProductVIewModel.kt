package com.example.inventarioapp_pdm.screen.new_product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventarioapp_pdm.domain.model.Product
import com.example.inventarioapp_pdm.ui.theme.Background
import com.example.inventarioapp_pdm.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductViewModel(
    categories: List<String>, // Ahora recibe solo los nombres de las categorías
    onBack: () -> Unit = {},
    onSave: (Product) -> Unit = {}
) {
    // Aquí guardamos lo que el usuario va escribiendo
    var productName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var selectedPresentation by remember { mutableStateOf("Caja") }
    var quantity by remember { mutableStateOf("") }
    var purchasePrice by remember { mutableStateOf("") }
    var salePrice by remember { mutableStateOf("") }

    // Control de categorías dinámicas
    var expandirCategorias by remember { mutableStateOf(false) }
    val listaCategorias = (categories.filter { it.isNotEmpty() } ).distinct()
    var esNuevaCategoria by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
    ) {
        // La barrita de arriba para no perderse
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Nuevo producto",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(48.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Botones para meter datos: Foto, Scanner o a mano
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionInputCard(
                    title = "Tomar foto",
                    icon = Icons.Default.AddAPhoto,
                    modifier = Modifier.weight(1f)
                )
                ActionInputCard(
                    title = "Escanear código",
                    icon = Icons.Default.QrCodeScanner,
                    modifier = Modifier.weight(1f)
                )
                ActionInputCard(
                    title = "Entrada manual",
                    icon = Icons.Default.Edit,
                    modifier = Modifier.weight(1f),
                    isSelected = true
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Los campos para llenar la info del producto
            NewProductTextField(
                value = productName,
                onValueChange = { productName = it },
                label = "Nombre del producto",
                placeholder = "Ingresa el nombre"
            )

            // Selector de Categoría (Nivel Intermedio y Funcional)
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                Text(
                    text = "Categoría",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                ExposedDropdownMenuBox(
                    expanded = expandirCategorias,
                    onExpandedChange = { expandirCategorias = !expandirCategorias }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = { if (esNuevaCategoria) category = it },
                        placeholder = { Text("Selecciona o escribe una categoría", color = Color.Gray, fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true),
                        shape = RoundedCornerShape(12.dp),
                        readOnly = !esNuevaCategoria,
                        trailingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (esNuevaCategoria) {
                                    IconButton(onClick = { 
                                        esNuevaCategoria = false
                                        category = categories.firstOrNull() ?: ""
                                    }) {
                                        Icon(Icons.Default.Edit, contentDescription = "Cancelar", tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                                    }
                                }
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirCategorias)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = PrimaryGreen
                        )
                    )
                    
                    ExposedDropdownMenu(
                        expanded = expandirCategorias,
                        onDismissRequest = { expandirCategorias = false }
                    ) {
                        listaCategorias.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    if (opcion == "Nueva categoría...") {
                                        esNuevaCategoria = true
                                        category = ""
                                    } else {
                                        esNuevaCategoria = false
                                        category = opcion
                                    }
                                    expandirCategorias = false
                                }
                            )
                        }
                    }
                }
            }

            // Selector de presentación (Caja o Unidad)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Presentación",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PresentationButton(
                        text = "Por caja",
                        isSelected = selectedPresentation == "Caja",
                        onClick = { selectedPresentation = "Caja" },
                        modifier = Modifier.weight(1f)
                    )
                    PresentationButton(
                        text = "Por unidad",
                        isSelected = selectedPresentation == "Unidad",
                        onClick = { selectedPresentation = "Unidad" },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            NewProductTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = "Cantidad disponible",
                placeholder = "Ingresa la cantidad",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            NewProductTextField(
                value = purchasePrice,
                onValueChange = { purchasePrice = it },
                label = "Precio de compra",
                placeholder = "Ingresa el precio",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            NewProductTextField(
                value = salePrice,
                onValueChange = { salePrice = it },
                label = "Precio de venta",
                placeholder = "Ingresa el precio",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // El botón final para guardar todo el relajo
            Button(
                onClick = {
                    val newProduct = Product(
                        name = productName,
                        category = category,
                        presentation = selectedPresentation,
                        stock = quantity.toIntOrNull() ?: 0,
                        purchasePrice = purchasePrice.toDoubleOrNull() ?: 0.0,
                        salePrice = salePrice.toDoubleOrNull() ?: 0.0,
                        status = "Activo"
                    )
                    onSave(newProduct)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text(
                    text = "Guardar producto",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ActionInputCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    // Una tarjetita para las acciones de arriba
    Surface(
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color.White else Color.Transparent,
        border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        shadowElevation = if (isSelected) 2.dp else 0.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) PrimaryGreen else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) PrimaryGreen else Color.Gray
            )
        }
    }
}

@Composable
fun NewProductTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isReadOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    // Un campo de texto personalizado para que se vea igual al diseño
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            readOnly = isReadOnly,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = PrimaryGreen
            )
        )
    }
}

@Composable
fun PresentationButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Botones para elegir si es por caja o unidad
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) PrimaryGreen.copy(alpha = 0.1f) else Color.White,
            contentColor = if (isSelected) PrimaryGreen else Color.Gray
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) PrimaryGreen else Color.LightGray.copy(alpha = 0.5f)
        )
    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium)
    }
}
