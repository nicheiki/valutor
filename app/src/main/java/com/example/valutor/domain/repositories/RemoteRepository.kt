package com.example.valutor.domain.repositories
import com.example.valutor.data.db.models.Ticker
import com.example.valutor.domain.models.Rate
import com.example.valutor.domain.models.SortOrder

interface RemoteRepository {

    suspend fun getCurrentRates(baseCurrency: String): List<Rate>

}