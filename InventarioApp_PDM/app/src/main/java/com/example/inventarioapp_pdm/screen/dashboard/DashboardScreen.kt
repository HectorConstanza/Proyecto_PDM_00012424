package com.example.inventarioapp_pdm.screen.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import com.example.inventarioapp_pdm.components.BottomNavigationBar

@Composable
fun DashboardScreen(
    onNavigate: (String) -> Unit = {}
) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "dashboard",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->

        DashboardViewModel(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            onNavigate = onNavigate
        )
    }
}
