package com.example.valutor.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    suspend fun fetchRates(@Query("base") baseCurrency: String): Response<String>

}

