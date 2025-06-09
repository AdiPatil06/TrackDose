package com.app.trackdosekotlin.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.trackdosekotlin.tabs.health.ui.navigation.HealthNavGraph
import com.app.trackdosekotlin.tabs.medicine.ui.navigation.MedicineNavGraph

sealed class MasterNavigationScreens(val route: String) {
    object Health : MasterNavigationScreens("Health")
    object Medicine : MasterNavigationScreens("Medicine")
}

@Composable
fun MasterNavigation() {
    val navController = rememberNavController()
    app.sharedViewModel.masterNavController = navController

    NavHost(
        navController = navController,
        startDestination = app.sharedViewModel.selectedNavTab.value
    ) {
        composable(route = MasterNavigationScreens.Health.route) {
            HealthNavGraph()
        }
        composable(route = MasterNavigationScreens.Medicine.route) {
            MedicineNavGraph()
        }
    }
}