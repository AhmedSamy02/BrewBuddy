package com.example.brewbuddy.data.remote.api

import retrofit2.http.GET

interface CoffeeApiService {
    @GET("coffee/hot")
    
    @GET("coffee/iced")
}