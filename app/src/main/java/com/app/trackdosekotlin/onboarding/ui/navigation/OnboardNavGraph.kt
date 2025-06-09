package com.app.trackdosekotlin.onboarding.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.trackdosekotlin.main.MasterNavigation
import com.app.trackdosekotlin.onboarding.ui.screens.SplashScreen
import com.app.trackdosekotlin.onboarding.ui.viewmodel.OnboardViewModel

sealed class OnboardScreens(val route: String) {
    object SplashScreen : OnboardScreens("splash")
    object MasterNav : OnboardScreens("master_nav")
}

@Composable
fun OnboardNavGraph() {
    val onboardViewModel: OnboardViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnboardScreens.SplashScreen.route
    ) {
        composable(route = OnboardScreens.SplashScreen.route) {
            SplashScreen(
                viewModel = onboardViewModel,
                onFinished = {
                    navController.navigate(OnboardScreens.MasterNav.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(route = OnboardScreens.MasterNav.route) {
            MasterNavigation()
        }
    }
}