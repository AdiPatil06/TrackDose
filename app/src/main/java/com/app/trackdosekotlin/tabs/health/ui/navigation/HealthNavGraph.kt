package com.app.trackdosekotlin.tabs.health.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.trackdosekotlin.tabs.health.ui.screens.HealthHomeScreen
import com.app.trackdosekotlin.tabs.health.ui.viewmodel.HealthViewModel

sealed class HealthNavScreens(val route: String) {
    object HealthHomeScreen : HealthNavScreens("health_home")
}

@Composable
fun HealthNavGraph() {
    val healthViewModel: HealthViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HealthNavScreens.HealthHomeScreen.route
    ) {
        composable(route = HealthNavScreens.HealthHomeScreen.route) {
            HealthHomeScreen(healthViewModel)
        }
    }
}