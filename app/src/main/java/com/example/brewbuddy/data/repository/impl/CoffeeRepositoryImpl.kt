package com.example.brewbuddy.data.repository.impl

import com.example.brewbuddy.data.remote.api.CoffeeApiService
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.model.CoffeeCategory
import com.example.brewbuddy.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeApiService: CoffeeApiService
) : CoffeeRepository {

    override fun getCoffeesByCategory(category: CoffeeCategory): Flow<List<Coffee>> {
        // TODO: Implement getting coffees by category
        TODO("Not yet implemented")
    }

    override fun getFavoriteCoffees(): Flow<List<Coffee>> {
        // TODO: Implement getting favorite coffees
        TODO("Not yet implemented")
    }

    override fun searchCoffees(query: String): Flow<List<Coffee>> {
        // TODO: Implement searching coffees
        TODO("Not yet implemented")
    }

    override suspend fun refreshCoffees() {
        // TODO: Implement refreshing coffees from API
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavorite(coffeeId: Int, isFavorite: Boolean) {
        // TODO: Implement toggling favorite status
        TODO("Not yet implemented")
    }
}
