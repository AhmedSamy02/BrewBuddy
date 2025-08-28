package com.example.brewbuddy.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.brewbuddy.data.local.entity.OrderEntity

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY orderedAt DESC")
    suspend fun getAllOrders(): List<OrderEntity>

    @Insert
    suspend fun insert(order: OrderEntity)
}
