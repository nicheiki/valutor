package com.example.valutor.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.valutor.presentation.Screen
import com.example.valutor.presentation.models.RateItem
import com.example.valutor.presentation.viewModels.MainViewModel


@Composable
fun Rates(onlyFavorites: Boolean, viewModel: MainViewModel) {
    Column(Modifier.padding(24.dp)) {
        Row {
            Menu(
                baseCurrency = viewModel.baseCurrency.collectAsState(initial = "").value,
                menu = viewModel.menuState.collectAsState(initial = emptyList()).value,
                onSelect = { currency -> viewModel.setBaseCurrency(currency) }
            )
            Box(modifier = Modifier.padding(8.dp)) {
                IconButton(onClick = { viewModel.currentScreen.value = Screen.Settings }) {
                    Icon(Icons.Filled.Settings, contentDescription = null)
                }
            }
        }
        RateList(
            rates = viewModel.rates().collectAsState(initial = emptyList()).value
        ) { name: String, isFavorite: Boolean -> (viewModel.update(name, isFavorite)) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Menu(
    baseCurrency: String,
    menu: List<String>,
    onSelect: (name: String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            value = baseCurrency,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            menu.forEach { name ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onSelect(name)
                }) {
                    Text(text = name)
                }
            }
        }
    }
}

@Composable
fun RateList(
    rates: List<RateItem>,
    onClick: (String, Boolean) -> Unit,
    ) {
    if (rates.isNotEmpty()) {
        LazyColumn(modifier = Modifier.padding(4.dp)) {
            items(items = rates) { rateItem ->
                RateItem(
                    name = rateItem.name,
                    value = rateItem.value,
                    uiState = rateItem.isFavorite
                ) { onClick(rateItem.name, !rateItem.isFavorite.value) }
            }
        }
    }
}

@Composable
fun RateItem(
    name: String,
    value: Double,
    uiState: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(name, modifier = Modifier.weight(1f))
        Text(value.toString(), modifier = Modifier.weight(3f))
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = if (uiState.value) {
                    Icons.Outlined.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                tint = MaterialTheme.colors.primary,
                contentDescription = null
            )
        }
    }

}

