package com.example.brewbuddy.di

import com.example.brewbuddy.data.repository.FavoritesRepositoryImpl
import com.example.brewbuddy.data.repository.impl.CoffeeRepositoryImpl
import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoffeeRepository(
        coffeeRepositoryImpl: CoffeeRepositoryImpl
    ): CoffeeRepository


    @Binds
    @Singleton
    abstract fun  bindFavoriteRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository

    // TODO: Add other repository bindings
}
