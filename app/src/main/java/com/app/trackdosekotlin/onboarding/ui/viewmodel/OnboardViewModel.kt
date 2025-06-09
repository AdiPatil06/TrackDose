package com.app.trackdosekotlin.onboarding.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackdosekotlin.main.app
import com.app.trackdosekotlin.shared.TDDataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OnboardViewModel : ViewModel() {

    private val TAG = "OnboardViewModel"

    var isSplashScreenDone = mutableStateOf(false)

    init {
        getSplashScreenStatus()
    }

    fun getSplashScreenStatus() {
        viewModelScope.launch {
            app.preferenceDataStore.getDataBoolean(TDDataStore.splashScreen).collectLatest {
                isSplashScreenDone.value = it == true
            }
        }
    }
}