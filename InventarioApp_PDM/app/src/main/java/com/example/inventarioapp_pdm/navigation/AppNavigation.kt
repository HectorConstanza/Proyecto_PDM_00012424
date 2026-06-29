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
import com.example.inventarioapp_pdm.screen.categories.CategoryScreen
import com.example.inventarioapp_pdm.screen.onboarding.OnboardingScreen
import com.example.inventarioapp_pdm.screen.login.LoginScreen

@Composable
fun AppNavigation() {
    // El control remoto de la navegación de toda la app
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Onboarding.route
    ) {
        // Pantalla de bienvenida (la que tiene la caja verde)
        composable(Routes.Onboarding.route) {
            OnboardingScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de login para entrar a la app
        composable(Routes.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla principal donde se ve el resumen del negocio
        composable(Routes.Dashboard.route) {
            DashboardScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }
        
        // La lista con todos los productos guardados en Room
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

        // El detalle completo de un producto (precios, stock, etc)
        composable(Routes.ProductDetail.route) {
            ProductDetailScreen(
                onBack = { navController.popBackStack() },
                onEdit = { /* Por hacer */ },
                onDispatch = { /* Por hacer */ }
            )
        }

        // Formulario para meter mercadería nueva al sistema
        composable(Routes.NewProduct.route) {
            NewProductScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de chismes y avisos de stock bajo
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

        // El perfil del admin con sus opciones
        composable(Routes.Profile.route) {
            ProfileScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // Gestión de categorías del catálogo
        composable(Routes.Categories.route) {
            CategoryScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
