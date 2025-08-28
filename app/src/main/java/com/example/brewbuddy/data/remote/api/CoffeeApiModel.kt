package com.example.brewbuddy.data.remote.api

data class CoffeeApiModel(
    val id: Int?,
    val title: String?,
    val description: String?,
    val ingredients: List<String>?,
    val image: String?
){
    val price: String
        get() = "$${(3..7).random()}.99"
}