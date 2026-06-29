package com.example.inventarioapp_pdm.screen.categories

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventarioapp_pdm.InventarioApp
import com.example.inventarioapp_pdm.ui.viewmodel.CategoryManagementViewModel

@Composable
fun CategoryScreen(
    onBack: () -> Unit = {}
) {
    val app = LocalContext.current.applicationContext as InventarioApp
    val factory = CategoryManagementViewModel.Factory(app.categoryRepository)
    val viewModel: CategoryManagementViewModel = viewModel(factory = factory)

    val categories by viewModel.categories.collectAsStateWithLifecycle()

    CategoryViewModel(
        categories = categories,
        onBack = onBack,
        onAddCategory = { viewModel.addCategory(it) }
    )
}
