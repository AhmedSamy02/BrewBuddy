package com.example.brewbuddy.di

import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.usecase.GetBestSellerCoffeeUseCase
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
    fun provideGetCoffeesByCategoryUseCase(repository: CoffeeRepository): GetCoffeesByCategoryUseCase {
        return GetCoffeesByCategoryUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetBestSellerCoffeeUseCase(repository: CoffeeRepository): GetBestSellerCoffeeUseCase{
        return GetBestSellerCoffeeUseCase(repository)
    }
}