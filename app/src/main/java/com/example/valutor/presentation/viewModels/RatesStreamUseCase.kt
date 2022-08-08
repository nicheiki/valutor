package com.example.valutor.presentation.viewModels

import com.example.valutor.domain.models.Rate
import com.example.valutor.presentation.Screen
import kotlinx.coroutines.flow.Flow

interface RatesStreamUseCase {

    fun flow(): Flow<List<Rate>>

}