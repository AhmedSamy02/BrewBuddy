package com.example.brewbuddy.di
import com.example.brewbuddy.data.repository.CoffeeRepositoryImpl
import com.example.brewbuddy.data.repository.impl.FavoritesRepositoryImpl
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
        impl: CoffeeRepositoryImpl
    ): CoffeeRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        impl: FavoritesRepositoryImpl
    ): FavoritesRepository
}
