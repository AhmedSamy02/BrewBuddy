package com.example.brewbuddy.DAO
import androidx.room.*
import com.example.brewbuddy.data.local.entity.CoffeeEntity

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffees WHERE category = :category")
    suspend fun getByCategory(category: String): List<CoffeeEntity>

    @Query("SELECT * FROM coffees")
    suspend fun getAll(): List<CoffeeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<CoffeeEntity>)

    @Query("DELETE FROM coffees")
    suspend fun clearAll()
}
