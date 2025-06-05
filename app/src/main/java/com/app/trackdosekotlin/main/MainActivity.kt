package com.app.trackdosekotlin.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.trackdosekotlin.mainUi.theme.TrackdoseKotlinTheme
import com.app.trackdosekotlin.onboarding.ui.navigation.OnboardNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackdoseKotlinTheme {
                OnboardNavGraph()
            }
        }
    }
}