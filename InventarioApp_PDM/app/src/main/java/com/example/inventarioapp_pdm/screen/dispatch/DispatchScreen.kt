package com.example.inventarioapp_pdm.screen.dispatch

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
fun DispatchScreen(
    onNavigate: (String) -> Unit = {}
) {
    // Obtenemos el repositorio desde la clase Application
    val app = LocalContext.current.applicationContext as InventarioApp
    val factory = ProductViewModel.Factory(app.productRepository, app.dispatchRepository, categoryRepo = app.categoryRepository)
    val viewModel: ProductViewModel = viewModel(factory = factory)
    
    // Observamos los productos para poder seleccionarlos
    val products by viewModel.allProducts.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "dispatch",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        // Aquí mandamos a llamar al diseño principal
        DispatchViewModel(
            products = products,
            onBack = { onNavigate("dashboard") },
            onConfirm = { product, quantity ->
                // Guardamos el despacho y bajamos el stock de una vez
                viewModel.recordDispatch(product, quantity)
                onNavigate("dashboard") 
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }
}
