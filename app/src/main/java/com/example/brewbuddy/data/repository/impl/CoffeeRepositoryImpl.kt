package com.example.brewbuddy.data.repository.impl
import com.example.brewbuddy.data.local.database.dao.CoffeeDao
import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import com.example.brewbuddy.data.remote.api.CoffeeApiModel
import com.example.brewbuddy.data.remote.api.CoffeeApiService
import com.example.brewbuddy.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val apiService: CoffeeApiService,
    private val coffeeDao: CoffeeDao
) : CoffeeRepository {

    override suspend fun getHotCoffee(): List<CoffeeApiModel> {
        return apiService.getHot()
    }

    override suspend fun getIcedCoffee(): List<CoffeeApiModel> {
        return apiService.getIced()
    }

    override fun getAllCoffees(): Flow<List<CoffeeEntity>> {
        return coffeeDao.getAllCoffees()
    }

    override fun getCoffeeById(id: Int): Flow<CoffeeEntity?> {
        return coffeeDao.getCoffeeById(id)
    }

    override suspend fun refreshCoffees() {
        val hotCoffees = apiService.getHot()
        val icedCoffees = apiService.getIced()

        val entities = (hotCoffees + icedCoffees).map {
            CoffeeEntity(
                id = it.id ?: 0,
                title = it.title ?: "Unknown",
                description = it.description ?: "No description available",
                imageUrl = it.image ?: "",
                ingredients = it.ingredients?.joinToString(", ") ?: "",
                price = (5..10).random().toDouble(), // mock price
                category = if (hotCoffees.contains(it)) "HOT" else "ICED"
            )
        }

        coffeeDao.insertCoffees(entities)
    }
}
