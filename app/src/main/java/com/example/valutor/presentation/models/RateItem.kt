package com.example.valutor.presentation.models

import androidx.compose.runtime.MutableState

data class RateItem(val name: String, val value: Double, var isFavorite: MutableState<Boolean>)

