package com.example.brewbuddy.di

import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.usecase.home.GetBestSellerCoffeeUseCase
import com.example.brewbuddy.domain.usecase.GetCoffeesByCategoryUseCase
import com.example.brewbuddy.domain.usecase.home.GetWeekRecommendationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetCoffeesByCategoryUseCase(repository: CoffeeRepository): GetCoffeesByCategoryUseCase {
        return GetCoffeesByCategoryUseCase(repository)
    }
    @Provides
    @ViewModelScoped
    fun provideGetBestSellerCoffeeUseCase(repository: CoffeeRepository): GetBestSellerCoffeeUseCase{
        return GetBestSellerCoffeeUseCase(repository)
    }
    @Provides
    @ViewModelScoped
    fun provideGetWeekRecommendationUseCase(repository: CoffeeRepository): GetWeekRecommendationUseCase{
        return GetWeekRecommendationUseCase(repository)
    }
}