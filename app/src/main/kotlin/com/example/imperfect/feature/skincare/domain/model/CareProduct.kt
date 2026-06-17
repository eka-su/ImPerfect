package com.example.imperfect.feature.skincare.domain.model

data class CareProduct(
    val id: Int,
    val name: String,
    val brand: String?,
    val category: ProductCategory?,
    val description: String?,
    val imagePath: String?,
    val isFavorite: Boolean,
    val createdAt: String
)