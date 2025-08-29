
package com.example.brewbuddy.data.remote.api
import com.example.brewbuddy.data.remote.api.CoffeeApiModel
import retrofit2.http.GET

interface CoffeeApiService {

    @GET("coffee/hot")
    suspend fun getHot(): List<CoffeeApiModel>

    @GET("coffee/iced")
    suspend fun getIced(): List<CoffeeApiModel>
}
