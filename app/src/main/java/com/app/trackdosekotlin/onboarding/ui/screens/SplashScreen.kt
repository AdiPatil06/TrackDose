package com.app.trackdosekotlin.onboarding.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.app.trackdosekotlin.R
import com.app.trackdosekotlin.main.app
import com.app.trackdosekotlin.onboarding.ui.viewmodel.OnboardViewModel
import com.app.trackdosekotlin.shared.TDDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class SplashData(
    val imageRes: Int,
    val title: String,
    val description: String
)

@Composable
fun SplashScreen(viewModel: OnboardViewModel, onFinished: () -> Unit) {
    val screens = listOf(
        SplashData(R.drawable.trackdose, "Your partner in everyday wellness!", ""),
        SplashData(
            R.drawable.medicine,
            "Stay on Track with your Medicine",
            "Never forget your medication again. Track your doses with ease and peace of mind"
        ),
        SplashData(
            R.drawable.heart,
            "Monitor Your Health",
            "Track your wellness journey, from hydration to exercise, all in one place"
        )
    )

    var currentScreen by remember { mutableIntStateOf(0) }
    val progress = remember { Animatable(0f) }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(currentScreen) {
        isVisible = false
        delay(100)
        isVisible = true

        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 3000)
        )
        if (currentScreen < screens.lastIndex && !viewModel.isSplashScreenDone.value) {
            currentScreen++
        } else {
            viewModel.viewModelScope.launch {
                app.preferenceDataStore.setDataBoolean(TDDataStore.splashScreen, true)
                onFinished()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Crossfade(
            targetState = screens[currentScreen],
            animationSpec = tween(durationMillis = 1000)
        ) { screen ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = screen.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (screen.title.isNotEmpty()) {
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = screen.title,
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                }

                if (screen.description.isNotEmpty()) {
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = screen.description,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { progress.value },
                    modifier = Modifier.size(64.dp),
                    color = Color.Black,
                    strokeWidth = 4.dp,
                    trackColor = Color.LightGray,
                    strokeCap = StrokeCap.Round,
                )

                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        if (currentScreen < screens.lastIndex && !viewModel.isSplashScreenDone.value) {
                            currentScreen++
                        } else {
                            viewModel.viewModelScope.launch {
                                app.preferenceDataStore.setDataBoolean(
                                    TDDataStore.splashScreen,
                                    true
                                )
                                onFinished()
                            }
                        }
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                }
            }
        }
    }
}