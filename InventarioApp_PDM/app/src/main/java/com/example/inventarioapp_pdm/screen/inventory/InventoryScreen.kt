package com.example.inventarioapp_pdm.screen.inventory

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventarioapp_pdm.components.BottomNavigationBar

@Composable
fun InventoryScreen(
    onNavigate: (String) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "inventory",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        InventoryContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            onBack = { onNavigate("dashboard") }
        )
    }
}
