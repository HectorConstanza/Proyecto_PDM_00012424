package com.example.inventarioapp_pdm.screen.new_product

import androidx.compose.runtime.Composable

@Composable
fun NewProductScreen(
    onBack: () -> Unit = {}
) {
    // Aquí mandamos a llamar a la UI del formulario con el nombre ViewModel como pediste
    NewProductViewModel(
        onBack = onBack,
        onSave = {
            // Aquí irá la lógica para guardar el producto después
            onBack()
        }
    )
}
