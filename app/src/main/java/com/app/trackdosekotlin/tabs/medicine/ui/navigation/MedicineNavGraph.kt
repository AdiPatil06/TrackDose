package com.app.trackdosekotlin.tabs.medicine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.trackdosekotlin.tabs.medicine.ui.screens.AddMedicineScreen
import com.app.trackdosekotlin.tabs.medicine.ui.screens.MedicineDetailsScreen
import com.app.trackdosekotlin.tabs.medicine.ui.screens.MedicineMainScreen
import com.app.trackdosekotlin.tabs.medicine.ui.viewmodel.MedicineViewModel

sealed class MedicineScreens(val route: String) {
    object MedicineMainScreen : MedicineScreens("medicine_main")
    object MedicineAddScreen : MedicineScreens("medicine_add")
    object MedicineDetailsScreen : MedicineScreens("medicine_details")
}

@Composable
fun MedicineNavGraph() {
    val medicineViewModel: MedicineViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MedicineScreens.MedicineMainScreen.route
    ) {
        composable(route = MedicineScreens.MedicineMainScreen.route) {
            MedicineMainScreen(
                medicineViewModel,
                onClick = {
                    when (it) {
                        "add" -> navController.navigate(MedicineScreens.MedicineAddScreen.route + "/-1")
                        "details" -> navController.navigate(MedicineScreens.MedicineDetailsScreen.route + "/${medicineViewModel.selectedReminder.value?.id}")
                    }
                }
            )
        }

        composable(
            route = MedicineScreens.MedicineAddScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.IntType
            })
        ) {
            val code = it.arguments?.getInt("id") ?: -1
            AddMedicineScreen(medicineViewModel, code) {
                navController.popBackStack()
            }
        }

        composable(
            route = MedicineScreens.MedicineDetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.IntType
            })
        ) {
            val code = it.arguments?.getInt("id") ?: -1
            MedicineDetailsScreen(medicineViewModel, code, onBackClick = {
                navController.popBackStack()
            }, onEditClick = {
                navController.navigate(MedicineScreens.MedicineAddScreen.route + "/${medicineViewModel.selectedReminder.value?.id}")
            })
        }
    }
}