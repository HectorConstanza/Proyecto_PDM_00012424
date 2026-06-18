package com.example.inventarioapp_pdm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventarioapp_pdm.screen.dashboard.DashboardScreen
import com.example.inventarioapp_pdm.screen.inventory.InventoryScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard.route
    ) {
        composable(Routes.Dashboard.route) {
            DashboardScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }
        
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
        
    }
}
