package com.app.trackdosekotlin.tabs.health.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.trackdosekotlin.shared.components.BottomBar
import com.app.trackdosekotlin.shared.components.TopBar
import com.app.trackdosekotlin.tabs.health.ui.viewmodel.HealthViewModel

@Composable
fun HealthHomeScreen(viewModel: HealthViewModel){
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {

        }
    }
}