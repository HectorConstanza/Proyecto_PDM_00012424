package com.example.inventarioapp_pdm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventarioapp_pdm.screen.dashboard.DashboardScreen
import com.example.inventarioapp_pdm.screen.inventory.InventoryScreen
import com.example.inventarioapp_pdm.screen.product_detail.ProductDetailScreen
import com.example.inventarioapp_pdm.screen.new_product.NewProductScreen
import com.example.inventarioapp_pdm.screen.notifications.NotificationsScreen
import com.example.inventarioapp_pdm.screen.dispatch.DispatchScreen
import com.example.inventarioapp_pdm.screen.profile.ProfileScreen

@Composable
fun AppNavigation() {
    // El control remoto de la navegación de toda la app
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard.route
    ) {
        // Pantalla principal donde se ve el resumen
        composable(Routes.Dashboard.route) {
            DashboardScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }
        
        // La lista con todos los productos del inventario
        composable(Routes.Inventory.route) {
            InventoryScreen(
                onNavigate = { route ->
                    if (route == Routes.Dashboard.route) {
                        navController.popBackStack()
                    } else {
                        navController.navigate(route)
                    }
                }
            )
        }

        // El detalle completo de un solo producto
        composable(Routes.ProductDetail.route) {
            ProductDetailScreen(
                onBack = { navController.popBackStack() },
                onEdit = {  },
                onDispatch = {  }
            )
        }

        // Formulario para meter mercadería nueva
        composable(Routes.NewProduct.route) {
            NewProductScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de notificaciones y avisos de stock bajo
        composable(Routes.Notifications.route) {
            NotificationsScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // Pantalla para sacar productos (Despachos)
        composable(Routes.Dispatch.route) {
            DispatchScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // Pantalla con los datos del usuario logueado
        composable(Routes.Profile.route) {
            ProfileScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }
    }
}
