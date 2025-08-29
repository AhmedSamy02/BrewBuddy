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
            .addMigrations(MIGRATION_1_2) // 👈 keep data safe
            .build()
    }

    @Provides
    fun provideCoffeeDao(database: BrewBuddyDatabase): CoffeeDao {
        return database.coffeeDao()
    }

    @Provides
    fun provideFavoriteDao(database: BrewBuddyDatabase): FavoritesDao = database.favDao()



}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Adds a new column without wiping data
        database.execSQL("ALTER TABLE orders ADD COLUMN ")
    }
}
