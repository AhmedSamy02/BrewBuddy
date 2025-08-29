package com.example.brewbuddy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brewbuddy.data.local.database.dao.CoffeeDao
import com.example.brewbuddy.data.local.database.dao.FavoritesDao
import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import com.example.brewbuddy.data.local.database.entities.FavEntity


@Database(
    entities = [CoffeeEntity::class, FavEntity::class],
    version = 2,
    exportSchema = true
)
abstract class BrewBuddyDatabase : RoomDatabase() {
    abstract fun favDao(): FavoritesDao
    abstract fun coffeeDao(): CoffeeDao
}