package com.example.inventarioapp_pdm.screen.new_product

import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventarioapp_pdm.InventarioApp
import com.example.inventarioapp_pdm.ui.viewmodel.ProductViewModel

@Composable
fun NewProductScreen(
    onBack: () -> Unit = {}
) {
    // Obtenemos el repositorio desde la clase Application
    val app = LocalContext.current.applicationContext as InventarioApp
    val factory = ProductViewModel.Factory(app.productRepository, app.dispatchRepository, app.categoryRepository)
    val viewModel: ProductViewModel = viewModel(factory = factory)

    // Observamos los productos y las categorías reales
    val categories by viewModel.categories.collectAsStateWithLifecycle()

    // Aquí mandamos a llamar a la UI del formulario
    NewProductViewModel(
        categories = categories, // Pasamos las categorías reales de la tabla
        onBack = onBack,
        onSave = { product ->
            viewModel.addProduct(product)
            onBack()
        }
    )
}
