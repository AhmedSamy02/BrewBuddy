package com.example.brewbuddy.di

import com.example.brewbuddy.data.repository.impl.CoffeeRepositoryImpl
import com.example.brewbuddy.domain.repository.CoffeeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCoffeeRepository(
        coffeeRepositoryImpl: CoffeeRepositoryImpl
    ): CoffeeRepository

    // TODO: Add other repository bindings
}
