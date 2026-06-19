package com.example.inventarioapp_pdm.domain.model

data class Product(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    //esto es para saber si el producto viene varios en una sola caja o vienen una por unidad
    //como por ejemplos los ventiladores ya que una caja de ventiladores trae 4 unidades
    val presentation: String = "",
    val stock: Int = 0,
    val unit: String = "unidades",
    val purchasePrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val status: String = "Activo",
    val imageUrl: String = ""
)
