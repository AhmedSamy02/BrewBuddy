package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.local.database.dao.CoffeeDao
import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import com.example.brewbuddy.data.remote.api.CoffeeApiModel
import com.example.brewbuddy.data.remote.api.CoffeeApiService
import com.example.brewbuddy.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoffeeRepositoryImpl @Inject constructor(
    private val api: CoffeeApiService,
    private val dao: CoffeeDao
) : CoffeeRepository {

    override suspend fun getCoffeesByCategory(category: String): List<CoffeeEntity> =
        dao.getCoffeesByCategory(category)

    override suspend fun fetchHotCoffees(): List<CoffeeEntity> {
        val data = api.getHotCoffees()
        val entities = data.map { it.toEntity("HOT") }
        dao.insertCoffees(entities)
        return entities
    }

    override suspend fun fetchColdCoffees(): List<CoffeeEntity> {
        val data = api.getIcedCoffees()
        val entities = data.map { it.toEntity("COLD") }
        dao.insertCoffees(entities)
        return entities
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
