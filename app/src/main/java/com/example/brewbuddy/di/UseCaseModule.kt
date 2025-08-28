package com.example.brewbuddy.di

import com.example.brewbuddy.domain.repository.CoffeeRepository
import com.example.brewbuddy.domain.repository.FavoritesRepository
import com.example.brewbuddy.domain.usecase.GetCoffeesByCategoryUseCase
import com.example.brewbuddy.domain.usecase.favorites.AddFavoriteUseCase
import com.example.brewbuddy.domain.usecase.favorites.FavoriteUseCase
import com.example.brewbuddy.domain.usecase.favorites.GetFavoriteUseCase
import com.example.brewbuddy.domain.usecase.favorites.RemoveFavoriteUseCase
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

    @Provides
    @ViewModelScoped
    fun  provideFavoriteUseCase(
        addFavoriteUseCase :AddFavoriteUseCase,
        removeFavoriteUseCase :RemoveFavoriteUseCase ,
        getFavoriteUseCase : GetFavoriteUseCase
    ): FavoriteUseCase{
        return FavoriteUseCase(
            addFavoriteUseCase =addFavoriteUseCase,
            removeFavoriteUseCase = removeFavoriteUseCase ,
            getFavoriteUseCase = getFavoriteUseCase ,
        )
    }
}
