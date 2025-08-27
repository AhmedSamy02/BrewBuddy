package com.example.brewbuddy.domain.model

data class Coffee(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val image: String,
    val price: Double,
    val category: CoffeeCategory,
    val isFavorite: Boolean = false
)

enum class CoffeeCategory {
    HOT, COLD
}
