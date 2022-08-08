package com.example.valutor.domain.usecases

import android.util.Log
import com.example.valutor.data.db.models.Ticker
import com.example.valutor.domain.models.Rate
import com.example.valutor.domain.models.SortOrder
import com.example.valutor.domain.repositories.LocalRepository
import com.example.valutor.domain.repositories.RemoteRepository
import com.example.valutor.domain.utils.FavoritesUtil
import com.example.valutor.domain.utils.SortUtil
import com.example.valutor.presentation.viewModels.RatesStreamUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DEFAULT_CURRENCY = "USD"
private const val BASE_CURRENCY = "baseCurrency"
private const val SORT_ORDER = "sortOrder"

class RatesStreamUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : RatesStreamUseCase {

    override fun flow(): Flow<List<Rate>> {
        return flow {
            combine(
                localRepository.string(BASE_CURRENCY),
                localRepository.tickers(),
                localRepository.string(SORT_ORDER).map { SortOrder.valueOf(it) }) { baseCurrency, tickers, sortOrder ->
                if (baseCurrency != lastCurrency) {
                    lastCurrency = baseCurrency
                    lastRates = remoteRepository.getCurrentRates(baseCurrency)
                    lastRates = SortUtil.sort(lastRates, sortOrder)
                    lastRates = FavoritesUtil.check(lastRates, tickers)
                } else if (sortOrder != lastSortOrder) {
                    lastSortOrder = sortOrder
                    lastRates = SortUtil.sort(lastRates, sortOrder)
                } else if (tickers != lastTickers) {
                    lastTickers = tickers
                    lastRates = FavoritesUtil.check(lastRates, tickers)
                }
                lastRates
            }.collect { list ->
                emit(list)
            }
        }
    }

    companion object {
        private var lastRates = listOf<Rate>()
        private var lastCurrency = DEFAULT_CURRENCY
        private var lastSortOrder = SortOrder.BY_NAME_ASC
        private var lastTickers = listOf<Ticker>()
    }

}