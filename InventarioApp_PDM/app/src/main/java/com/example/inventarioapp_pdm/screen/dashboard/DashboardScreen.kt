package com.example.inventarioapp_pdm.screen.dashboard

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
fun DashboardScreen(
    onNavigate: (String) -> Unit = {}
) {
    // Obtenemos el repositorio desde la clase Application
    val app = LocalContext.current.applicationContext as InventarioApp
    val factory = ProductViewModel.Factory(app.productRepository, app.dispatchRepository, categoryRepo = app.categoryRepository)
    val viewModel: ProductViewModel = viewModel(factory = factory)

    // Observamos los productos reales para las estadísticas
    val products by viewModel.allProducts.collectAsStateWithLifecycle()
    val todayDispatches by viewModel.todayDispatchesCount.collectAsStateWithLifecycle()
    
    val totalProducts = products.size
    val lowStockCount = products.count { it.stock < 10 }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "dashboard",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->

        DashboardViewModel(
            totalProducts = totalProducts,
            lowStockCount = lowStockCount,
            todayDispatches = todayDispatches,
            lowStockList = products.filter { it.stock < 10 },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            onNavigate = onNavigate
        )
    }
}
