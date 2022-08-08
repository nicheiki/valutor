package com.example.valutor.domain.utils

import com.example.valutor.data.db.models.Ticker
import com.example.valutor.domain.models.Rate

object FavoritesUtil {

    fun check(rates: List<Rate>, tickers: List<Ticker>): List<Rate> = rates.map {
        Rate(it.name, it.value, tickers.first { ticker -> ticker.name == it.name }.isFavorite)
    }

}