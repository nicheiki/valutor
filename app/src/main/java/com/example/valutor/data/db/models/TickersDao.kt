package com.example.valutor.data.db.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TickersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(tickers: List<Ticker>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(ticker: Ticker)

    @Query("SELECT * FROM tickers")
    fun tickers(): Flow<List<Ticker>>

    @Query("SELECT * FROM tickers")
    fun getTickers(): List<Ticker>

}
