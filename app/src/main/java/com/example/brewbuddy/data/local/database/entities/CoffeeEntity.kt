package com.example.brewbuddy.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coffees")
data class CoffeeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val ingredients: String, // comma separated
    val price: Double,
    val category: String // "HOT" or "COLD"
)
