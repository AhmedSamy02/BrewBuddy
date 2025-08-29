package com.example.brewbuddy.data.remote.api


data class CoffeeApiModel(
    val id: Int?,
    val title: String?,
    val description: String?,
    val ingredients: List<String>?,
    val image: String?
)
{
    // Fake price since API doesn’t provide it
    val price: String
        get() = "$${(3..7).random()}.99"
}
