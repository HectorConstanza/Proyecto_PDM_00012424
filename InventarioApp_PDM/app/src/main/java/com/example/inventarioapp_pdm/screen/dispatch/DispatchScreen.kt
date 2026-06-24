package com.example.inventarioapp_pdm.screen.dispatch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventarioapp_pdm.components.BottomNavigationBar

@Composable
fun DispatchScreen(
    onNavigate: (String) -> Unit = {}
) {
    // Estructura de la pantalla con su barra de navegación abajo
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "dispatch",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        // Aquí mandamos a llamar al diseño principal que está en el otro archivo
        DispatchViewModel(
            onBack = { onNavigate("dashboard") },
            onConfirm = { 
                // Aquí después pondremos la lógica para descontar del stock
                onNavigate("dashboard") 
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }
}
