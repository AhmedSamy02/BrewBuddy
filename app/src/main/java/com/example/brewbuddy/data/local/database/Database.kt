package com.example.brewbuddy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brewbuddy.data.local.database.dao.FavoritesDao
import com.example.brewbuddy.data.local.database.dao.OrderHistoryDao
import com.example.brewbuddy.data.local.database.entities.FavEntity
import com.example.brewbuddy.data.local.database.entities.OrderHistory

@Database(
    entities = [FavEntity::class, OrderHistory::class],
    version = 3,
    exportSchema = true
)
abstract class BrewBuddyDatabase : RoomDatabase() {
    abstract fun favDao() : FavoritesDao
    abstract fun orderHistoryDao() : OrderHistoryDao
}