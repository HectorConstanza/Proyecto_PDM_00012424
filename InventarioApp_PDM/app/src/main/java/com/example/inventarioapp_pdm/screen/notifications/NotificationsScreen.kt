package com.example.inventarioapp_pdm.screen.notifications

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventarioapp_pdm.components.BottomNavigationBar

@Composable
fun NotificationsScreen(
    onNavigate: (String) -> Unit = {}
) {
    // El andamio de la pantalla con su barrita de abajo
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "notifications",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        // Aquí mandamos a llamar a la UI que armamos
        NotificationsViewModel(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }
}
