package com.example.inventarioapp_pdm.screen.inventory

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventarioapp_pdm.InventarioApp
import com.example.inventarioapp_pdm.components.BottomNavigationBar
import com.example.inventarioapp_pdm.ui.viewmodel.ProductViewModel

@Composable
fun InventoryScreen(
    onNavigate: (String) -> Unit = {}
) {
    // Obtenemos el repositorio desde la clase Application
    val app = LocalContext.current.applicationContext as InventarioApp
    val factory = ProductViewModel.Factory(app.productRepository, app.dispatchRepository, categoryRepo = app.categoryRepository)
    val viewModel: ProductViewModel = viewModel(factory = factory)
    
    // Observamos la lista de productos de Room
    val products by viewModel.allProducts.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "inventory",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        InventoryViewModel(
            products = products, // Pasamos los productos reales
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            onBack = { onNavigate("dashboard") },
            onProductClick = { product ->
                onNavigate("product_detail")
            }
        )
    }
}
