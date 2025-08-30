package com.example.brewbuddy.di

import com.example.brewbuddy.data.repository.impl.FavoritesRepositoryImpl
import com.example.brewbuddy.data.repository.impl.CoffeeRepositoryImpl
import com.example.brewbuddy.data.repository.impl.OrderHistoryRepositoryImp
import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.repository.FavoritesRepository
import com.example.brewbuddy.domain.repository.OrderHistoryRepository
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

    @Binds
    @Singleton
    abstract fun bindOrderHistoryRepository(
        orderHistoryRepositoryImp: OrderHistoryRepositoryImp
    ): OrderHistoryRepository

    // TODO: Add other repository bindings
}
