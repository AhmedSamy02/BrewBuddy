package com.example.brewbuddy.data.repository.impl

import com.example.brewbuddy.data.remote.api.CoffeeApiService
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
    private val api: CoffeeApiService,
    private val dao: CoffeeDao
) : CoffeeRepository {
    private var icedData = emptyList<CoffeeDto>()
    private var hotData = emptyList<CoffeeDto>()
    override fun getCoffeesByCategory(category: CoffeeCategory): Flow<List<Coffee>> {
        // TODO: Implement getting coffees by category
        TODO("Not yet implemented")

    override suspend fun getCoffeesByCategory(category: String): List<CoffeeEntity> =
        dao.getCoffeesByCategory(category)

    override suspend fun fetchHotCoffees(): List<CoffeeEntity> {
        return try {
            val data = api.getHotCoffees()
            val entities = data.map { it.toEntity("HOT") }
            dao.insertCoffees(entities)
            entities
        } catch (e: Exception) {
            // fallback: seed if database empty
            if (dao.getCoffeeCount() == 0) {
                dao.insertCoffees(SampleCoffees.defaultCoffees)
                SampleCoffees.defaultCoffees
            } else {
                emptyList()
            }
        }
    }

    override suspend fun fetchColdCoffees(): List<CoffeeEntity> {
        return try {
            val data = api.getIcedCoffees()
            val entities = data.map { it.toEntity("COLD") }
            dao.insertCoffees(entities)
            entities
        } catch (e: Exception) {
            if (dao.getCoffeeCount() == 0) {
                dao.insertCoffees(SampleCoffees.defaultCoffees)
                SampleCoffees.defaultCoffees
            } else {
                emptyList()
            }
        }
    }

    override fun getCachedCoffees(): Flow<List<CoffeeEntity>> = dao.getAllCoffees()
}

// Mapper
fun CoffeeApiModel.toEntity(category: String) = CoffeeEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = image,
    ingredients = ingredients.joinToString(","),
    price = price,
    category = category
)
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
