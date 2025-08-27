package com.example.brewbuddy.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val coffeeId: Int,
    val name: String,
    val pricePerUnit: Double,
    val quantity: Int,
    val totalPrice: Double,
    val orderedAt: Long = System.currentTimeMillis()
)

