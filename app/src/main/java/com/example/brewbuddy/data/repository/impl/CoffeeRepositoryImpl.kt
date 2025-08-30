package com.example.brewbuddy.data.repository.impl

import android.util.Log
import com.example.brewbuddy.data.remote.api.CoffeeApiService
import com.example.brewbuddy.data.remote.dto.CoffeeDto
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.model.CoffeeCategory
import com.example.brewbuddy.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeApiService: CoffeeApiService
) : CoffeeRepository {
    private var icedData = emptyList<CoffeeDto>()
    private var hotData = emptyList<CoffeeDto>()
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

    override suspend fun getBestSellerCoffee(): Coffee {
        val data = getAllCoffees()
        val coffee = data.random()
        return Coffee.fromDto(
            coffee,
            if (coffee in icedData) CoffeeCategory.COLD else CoffeeCategory.HOT
        )
    }

    override suspend fun getWeekRecommendation(): List<Coffee> {
        val data = getAllCoffees()
        return data.shuffled().take(8).map { coffee ->
            Coffee.fromDto(
                coffee, if (coffee in icedData) CoffeeCategory.COLD else CoffeeCategory.HOT
            )
        }
    }

    private suspend fun getAllCoffees(): List<CoffeeDto> {
        if(icedData.isEmpty()){
            icedData = coffeeApiService.getIcedCoffees()
        }
        if(hotData.isEmpty()){
            hotData = coffeeApiService.getHotCoffees()
        }
        return icedData+hotData
    }
}
