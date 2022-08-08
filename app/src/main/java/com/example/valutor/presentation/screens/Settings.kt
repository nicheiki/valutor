package com.example.valutor.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valutor.domain.models.SortOrder
import com.example.valutor.presentation.viewModels.MainViewModel

@Composable
fun Settings(viewModel: MainViewModel) {
    Column(Modifier.padding(24.dp)) {
        Text(text = "Сортировать", modifier = Modifier.padding(bottom = 8.dp))
        OptionsMenu(
            sortOrder = viewModel.sortOrder.collectAsState(initial = SortOrder.BY_NAME_ASC).value,
            menu = SortOrder.values().toList(),
            onSelect = { sortOrder -> viewModel.setSortOrder(sortOrder) }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OptionsMenu(
    sortOrder: SortOrder,
    menu: List<SortOrder>,
    onSelect: (sortOrder: SortOrder) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }


    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            maxLines = 1,
            value = sortOrder.text,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            menu.forEach {
                DropdownMenuItem(onClick = {
                    onSelect(it)
                    expanded = false
                }) {
                    Text(text = it.text)
                }
            }
        }
    }
}