package com.example.brewbuddy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brewbuddy.data.local.database.dao.FavoritesDao
import com.example.brewbuddy.data.local.database.entities.FavEntity

@Database(
    entities = [FavEntity::class,],
    version = 1,
    exportSchema = true
)
abstract class BrewBuddyDatabase : RoomDatabase() {
    abstract fun favDao() : FavoritesDao
}