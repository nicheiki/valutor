package com.example.valutor.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

enum class Screen(val onlyFavorites: Boolean) {
    Popular(onlyFavorites = false),
    Favorites(onlyFavorites = true),
    Settings(onlyFavorites = false)
}