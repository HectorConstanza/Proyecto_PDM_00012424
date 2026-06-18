package com.example.inventarioapp_pdm.domain.model

data class Product(
    val name: String,
    val category: String,
    val stock: Int,
    val unit: String
)
