package com.example.inventarioapp_pdm.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventarioapp_pdm.components.BottomNavigationBar

@Composable
fun ProfileScreen(
    onNavigate: (String) -> Unit = {}
) {
    // La base con la barra de navegación de siempre
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "profile",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        // Aquí cargamos todo el diseño del perfil
        ProfileViewModel(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            onBack = { onNavigate("dashboard") },
            onLogout = { 
                // Por ahora solo nos manda al login de mentirillas
                onNavigate("login") 
            }
        )
    }
}
