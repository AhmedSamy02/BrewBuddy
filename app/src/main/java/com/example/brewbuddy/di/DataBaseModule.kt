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
import com.example.brewbuddy.data.local.database.dao.CoffeeDao
import com.example.brewbuddy.data.local.database.dao.FavoritesDao
import com.example.brewbuddy.data.remote.api.CoffeeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BrewBuddyDatabase =
        Room.databaseBuilder(
            context,
            BrewBuddyDatabase::class.java,
            "brewbuddy_db"
        ).build()

    @Provides
    fun provideCoffeeDao(db: BrewBuddyDatabase): CoffeeDao = db.coffeeDao()

    @Provides
    fun provideFavoritesDao(db: BrewBuddyDatabase): FavoritesDao = db.favDao()
}
