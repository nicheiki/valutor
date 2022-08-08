package com.example.valutor.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.valutor.presentation.Screen
import com.example.valutor.presentation.viewModels.MainViewModel


@Composable
fun Navigation(
    viewModel: MainViewModel,
    screen: MutableState<Screen>
) {
    when (screen.value) {
        Screen.Popular -> Rates(onlyFavorites = Screen.Popular.onlyFavorites, viewModel = viewModel)
        Screen.Favorites -> Rates(onlyFavorites = Screen.Favorites.onlyFavorites, viewModel = viewModel)
        Screen.Settings -> Settings(viewModel)
    }
}