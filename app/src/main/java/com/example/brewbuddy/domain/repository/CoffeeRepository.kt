package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    suspend fun getCoffeesByCategory(category: String): List<CoffeeEntity>
    suspend fun fetchHotCoffees(): List<CoffeeEntity>
    suspend fun fetchColdCoffees(): List<CoffeeEntity>
    fun getCachedCoffees(): Flow<List<CoffeeEntity>>
}
