package com.example.inventarioapp_pdm.screen.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import com.example.inventarioapp_pdm.components.BottomNavigationBar

@Composable
fun DashboardScreen() {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "dashboard",
                onNavigate = { route ->
                    // Aquí manejarías la navegación real con NavController
                }
            )
        }
    ) { paddingValues ->

        DashboardContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }
}
