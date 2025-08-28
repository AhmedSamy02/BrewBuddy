package com.example.brewbuddy.di

import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.usecase.GetCoffeesByCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCoffeesByCategoryUseCase(repository: CoffeeRepository): GetCoffeesByCategoryUseCase{
        return GetCoffeesByCategoryUseCase(repository)
    }
}