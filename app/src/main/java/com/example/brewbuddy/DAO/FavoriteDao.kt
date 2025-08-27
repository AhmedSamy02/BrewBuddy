package com.example.brewbuddy.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brewbuddy.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(fav: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE coffeeId = :id")
    suspend fun remove(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE coffeeId = :id)")
    suspend fun isFavorite(id: Int): Boolean
}
