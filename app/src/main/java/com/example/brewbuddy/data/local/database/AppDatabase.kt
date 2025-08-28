package com.example.brewbuddy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brewbuddy.DAO.CoffeeDao
import com.example.brewbuddy.DAO.FavoriteDao
import com.example.brewbuddy.DAO.OrderDao
import com.example.brewbuddy.data.local.entity.CoffeeEntity
import com.example.brewbuddy.data.local.entity.FavoriteEntity
import com.example.brewbuddy.data.local.entity.OrderEntity

@Database(
    entities = [
        CoffeeEntity::class,
        FavoriteEntity::class,
        OrderEntity::class
    ],
    version = AppConfig.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun orderDao(): OrderDao
}