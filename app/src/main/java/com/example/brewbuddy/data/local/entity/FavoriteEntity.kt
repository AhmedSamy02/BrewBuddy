package com.example.brewbuddy.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val coffeeId: Int,
    val savedAt: Long = System.currentTimeMillis()
)
