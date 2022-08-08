package com.example.valutor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HeatPump
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.valutor.data.di.ValutorApplication
import com.example.valutor.presentation.screens.Navigation
import com.example.valutor.presentation.viewModels.MainViewModel
import com.example.valutor.ui.theme.ValutorTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ValutorApplication).appComponent.inject(this)

        setContent {
            ValutorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ValutorApp(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun ValutorApp(
    viewModel: MainViewModel
) {
    ValutorTheme {
        Scaffold(
            bottomBar = {
                BottomBar { screen -> viewModel.navTo(screen) }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                Navigation(
                    viewModel = viewModel,
                    screen = viewModel.currentScreen
                )
            }
        }
    }
}

@Composable
fun BottomBar(onSelect: (Screen) -> Unit) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.HeatPump, contentDescription = null) },
            unselectedContentColor = MaterialTheme.colors.background,
            label = { Text(Screen.Popular.name) },
            selected = true,
            onClick = { onSelect(Screen.Popular) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            unselectedContentColor = MaterialTheme.colors.background,
            label = { Text(Screen.Favorites.name) },
            selected = false,
            onClick = { onSelect(Screen.Favorites) }
        )
    }
}
