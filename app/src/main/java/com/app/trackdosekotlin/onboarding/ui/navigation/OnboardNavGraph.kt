package com.app.trackdosekotlin.onboarding.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.trackdosekotlin.onboarding.ui.screens.SplashScreen
import com.app.trackdosekotlin.onboarding.ui.viewmodel.OnboardViewModel
import com.app.trackdosekotlin.tabs.health.ui.navigation.HealthNavGraph

sealed class OnboardScreens(val route: String) {
    object SplashScreen : OnboardScreens("splash")
    object HealthMainScreen : OnboardScreens("health_main")
}

@Composable
fun OnboardNavGraph(){
    val onboardViewModel : OnboardViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardScreens.SplashScreen.route
    ) {
        composable(route = OnboardScreens.SplashScreen.route) {
            SplashScreen(
                viewModel = onboardViewModel,
                onFinished = {
                    navController.navigate(OnboardScreens.HealthMainScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(route = OnboardScreens.HealthMainScreen.route) {
            HealthNavGraph()
        }
    }
}