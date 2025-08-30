package com.example.brewbuddy.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderHistory")
data class OrderHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val coffeeId: Int,
    val title: String,
    val image: String?,
    val date: String,
    val quantity: Int,
    val invoiceId:Int
)