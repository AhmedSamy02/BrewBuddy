package com.example.brewbuddy.data.remote.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.sampleapis.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: CoffeeApiService by lazy {
        retrofit.create(CoffeeApiService::class.java)
    }
}