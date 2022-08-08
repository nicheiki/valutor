package com.example.valutor.data.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

class Client @Inject constructor() {

    val api: ApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://api.exchangerate.host/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}