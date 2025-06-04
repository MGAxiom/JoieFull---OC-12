package com.example.joiefull.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val strikedPrice: Double,
    val rate: Double,
    val imageUrl: String,
    val imageDescription: String,
    val category: Category,
)

enum class Category {
    ACCESSORIES,
    BOTTOMS,
    SHOES,
    TOPS,
}