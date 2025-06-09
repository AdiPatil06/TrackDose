package com.app.trackdosekotlin.tabs.medicine.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.app.trackdosekotlin.shared.components.TopBar
import com.app.trackdosekotlin.tabs.medicine.ui.viewmodel.MedicineViewModel
import kotlinx.coroutines.flow.first

@Composable
fun MedicineDetailsScreen(
    viewModel: MedicineViewModel,
    code: Int?,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            if (code != -1) {
                viewModel.selectedReminder.value =
                    viewModel.allReminders.first().find { it.id == code }
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Medicine Details",
                isBackArrowRequired = true,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                Button(
                    onClick = {
                        onEditClick()
                    },
                    modifier = Modifier.weight(0.45f),
                ) {
                    Text(
                        "Edit",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        ),
                    )
                }
                Spacer(Modifier.weight(0.1f))
                Button(
                    onClick = {
                        viewModel.selectedReminder.value?.let {
                            viewModel.delete(it) {
                                onBackClick()
                            }
                        }
                    },
                    modifier = Modifier.weight(0.45f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(
                        "Delete",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        ),
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding() + 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                viewModel.selectedReminder.value?.medicineName ?: "",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                viewModel.selectedReminder.value?.dosageInformation ?: "",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Frequency",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                viewModel.selectedReminder.value?.frequency ?: "",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Reminder Types",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            viewModel.selectedReminder.value?.reminderTimes?.forEach {
                Text(
                    it,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(
                "Duration",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                viewModel.selectedReminder.value?.duration ?: "",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Notes",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                viewModel.selectedReminder.value?.additionalNotes ?: "",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}