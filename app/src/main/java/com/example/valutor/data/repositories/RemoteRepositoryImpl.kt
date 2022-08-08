package com.example.valutor.data.repositories

import com.example.valutor.data.mappers.JsonMapper
import com.example.valutor.data.network.Client
import com.example.valutor.domain.models.Rate
import com.example.valutor.domain.repositories.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val client: Client,
    private val converter: JsonMapper
) : RemoteRepository {

    override suspend fun getCurrentRates(baseCurrency: String): List<Rate> {
        val json = client.api.fetchRates(baseCurrency).body()!!
        return converter.jsonToRateList(json)
    }

}