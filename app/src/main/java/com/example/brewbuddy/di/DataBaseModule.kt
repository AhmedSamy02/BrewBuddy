package com.example.brewbuddy.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.brewbuddy.data.local.database.BrewBuddyDatabase
import com.example.brewbuddy.data.local.database.dao.FavoritesDao
import com.example.brewbuddy.data.local.database.dao.OrderHistoryDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BrewBuddyDatabase {
        return Room.databaseBuilder(
            context,
            BrewBuddyDatabase::class.java,
            "brewbuddy_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideFavoriteDao(database: BrewBuddyDatabase): FavoritesDao = database.favDao()
    @Provides
    fun provideOrderHistoryDao(database: BrewBuddyDatabase): OrderHistoryDao = database.orderHistoryDao()
}
