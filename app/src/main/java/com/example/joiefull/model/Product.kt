package com.example.joiefull.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val strikedPrice: Double,
    val rate: Double,
    val imageUrl: String,
    val imageDescription: String,
    val category: String,
    val rating: Float? = null,
    val comment: String? = null,
    val isFavorite: Boolean = false,
)
