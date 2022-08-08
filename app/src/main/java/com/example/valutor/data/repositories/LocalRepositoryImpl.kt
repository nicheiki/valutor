package com.example.valutor.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.valutor.data.db.models.Ticker
import com.example.valutor.data.db.models.TickersDao
import com.example.valutor.domain.models.SortOrder
import com.example.valutor.domain.repositories.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")

class LocalRepositoryImpl @Inject constructor(
    private val dao: TickersDao,
    private val context: Context,
) : LocalRepository {

    override suspend fun addTickers(tickers: List<Ticker>) {
        dao.addAll(tickers)
    }

    override suspend fun updateTicker(ticker: Ticker) {
        dao.update(ticker)
    }

    override fun tickers(): Flow<List<Ticker>> {
        return dao.tickers()
    }

    override suspend fun getTickers(): List<Ticker> {
        return dao.getTickers()
    }

    override suspend fun putString(preferencesKey: String, string: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(preferencesKey)] = string
        }
    }

    override fun string(preferencesKey: String): Flow<String> {
        return context.dataStore.data.map {
            it[stringPreferencesKey(preferencesKey)] ?: ""
        }
    }


}