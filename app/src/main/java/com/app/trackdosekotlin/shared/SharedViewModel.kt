package com.app.trackdosekotlin.shared

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.app.trackdosekotlin.main.MasterNavigationScreens

class SharedViewModel : ViewModel() {

    private val TAG = "SharedViewModel"

    lateinit var masterNavController: NavHostController

    var selectedNavTab = mutableStateOf(MasterNavigationScreens.Medicine.route)
}