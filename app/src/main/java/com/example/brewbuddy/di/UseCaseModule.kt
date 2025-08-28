package com.example.brewbuddy.di

import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.usecase.GetCoffeesByCategoryUseCase
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
    fun provideGetCoffeesByCategoryUseCase(repository: CoffeeRepository): GetCoffeesByCategoryUseCase{
        return GetCoffeesByCategoryUseCase(repository)
    }
}