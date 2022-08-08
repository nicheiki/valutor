package com.example.valutor.domain.repositories

import com.example.valutor.data.db.models.Ticker
import com.example.valutor.domain.models.SortOrder
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun addTickers(tickers: List<Ticker>)

    suspend fun updateTicker(ticker: Ticker)

    fun tickers(): Flow<List<Ticker>>

    suspend fun getTickers():  List<Ticker>

    suspend fun putString(preferencesKey: String, string: String)

    fun string(preferencesKey: String): Flow<String>

}