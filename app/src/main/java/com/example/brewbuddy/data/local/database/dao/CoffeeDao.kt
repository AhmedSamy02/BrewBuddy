// CoffeeDao.kt
package com.example.brewbuddy.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {

    @Query("SELECT COUNT(*) FROM coffees")
    suspend fun getCoffeeCount(): Int
    @Query("SELECT * FROM coffees")
    fun getAllCoffees(): Flow<List<CoffeeEntity>>
    @Query("SELECT * FROM coffees WHERE category = :category")
    suspend fun getCoffeesByCategory(category: String): List<CoffeeEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffees(coffees: List<CoffeeEntity>)
}
