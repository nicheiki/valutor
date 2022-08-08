package com.example.valutor.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valutor.data.db.models.Ticker
import com.example.valutor.domain.models.SortOrder
import com.example.valutor.domain.repositories.LocalRepository
import com.example.valutor.domain.repositories.RemoteRepository
import com.example.valutor.presentation.Screen
import com.example.valutor.presentation.models.RateItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_CURRENCY = "USD"
private const val BASE_CURRENCY = "baseCurrency"
private const val SORT_ORDER = "sortOrder"

class MainViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val ratesStreamUseCase: RatesStreamUseCase
) : ViewModel() {

    val currentScreen = mutableStateOf(Screen.Popular)
    val baseCurrency = localRepository.string(BASE_CURRENCY)
    val menuState = MutableStateFlow<List<String>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val savedTickers = localRepository.getTickers()
            if (savedTickers.isEmpty()) {
                val rates = remoteRepository.getCurrentRates(DEFAULT_CURRENCY)
                menuState.value = rates.map { it.name }
                localRepository.addTickers(rates.map { Ticker(it.name, false) })
            } else {
                menuState.value = savedTickers.map { it.name }
            }
        }
    }

    fun rates(): Flow<List<RateItem>> {
        return ratesStreamUseCase.flow()

            .map { rates ->
                rates.map { rate ->
                    RateItem(rate.name, rate.value, mutableStateOf(rate.isFavorite))
                }
            }
            .map { list ->
                list.filter { if (currentScreen.value.onlyFavorites) it.isFavorite.value else true }
            }

    }

    val sortOrder = localRepository.string(SORT_ORDER).map { string ->
        SortOrder.values().first { it.name == string }
    }

    fun navTo(screen: Screen) {
        currentScreen.value = screen
    }

    fun setBaseCurrency(currency: String) {
        viewModelScope.launch {
            localRepository.putString(BASE_CURRENCY, currency)
        }
    }

    fun update(name: String, isFavorite: Boolean) {
        viewModelScope.launch {
            localRepository.updateTicker(Ticker(name, isFavorite))
        }
    }

    fun setSortOrder(sortOrder: SortOrder) {
        viewModelScope.launch {
            localRepository.putString(SORT_ORDER, sortOrder.name)
        }
    }


}