package com.example.brewbuddy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffees")
data class CoffeeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String?,
    val ingredients: String,
    val imageUrl: String?,
    val price: Double,
    val category: String
)