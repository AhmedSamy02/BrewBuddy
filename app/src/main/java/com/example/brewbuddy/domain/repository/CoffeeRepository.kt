package com.example.brewbuddy.domain.repository

import com.example.brewbuddy.data.local.database.entities.CoffeeEntity
import com.example.brewbuddy.data.remote.api.CoffeeApiModel
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.model.CoffeeCategory
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {

    suspend fun getHotCoffee(): List<CoffeeApiModel>
    suspend fun getIcedCoffee(): List<CoffeeApiModel>

    fun getAllCoffees(): Flow<List<CoffeeEntity>>
    fun getCoffeeById(id: Int): Flow<CoffeeEntity?>
    suspend fun refreshCoffees()
}

