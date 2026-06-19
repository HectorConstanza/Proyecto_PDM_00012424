package com.example.inventarioapp_pdm.screen.product_detail

import androidx.compose.runtime.Composable
import com.example.inventarioapp_pdm.domain.model.Product

@Composable
fun ProductDetailScreen(
    onBack: () -> Unit = {},
    onEdit: (String) -> Unit = {},
    onDispatch: (String) -> Unit = {}
) {
    // Por ahora usamos un producto de ejemplo
    // Luego esto vendrá del ViewModel usando el ID
    val exampleProduct = Product(
        name = "Ventilador 18\"",
        category = "Ventiladores",
        presentation = "Caja",
        stock = 15,
        unit = "unidades",
        purchasePrice = 25.0,
        salePrice = 40.0,
        status = "Activo"
    )

    ProductDetailViewModel(
        product = exampleProduct,
        onBack = onBack,
        onEdit = { onEdit(exampleProduct.id) },
        onDispatch = { onDispatch(exampleProduct.id) }
    )
}
