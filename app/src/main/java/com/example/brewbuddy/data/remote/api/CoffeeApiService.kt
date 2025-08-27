package com.example.brewbuddy.data.remote.api

import com.example.brewbuddy.data.remote.dto.CoffeeDto
import retrofit2.http.GET

interface CoffeeApiService {
    
    @GET("coffee/hot")
    suspend fun getHotCoffees(): List<CoffeeDto>
    
    @GET("coffee/iced")
    suspend fun getIcedCoffees(): List<CoffeeDto>
}
