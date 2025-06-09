package com.app.trackdosekotlin.tabs.medicine.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.trackdosekotlin.R
import com.app.trackdosekotlin.shared.components.BottomBar
import com.app.trackdosekotlin.shared.components.TopBar
import com.app.trackdosekotlin.tabs.medicine.ui.viewmodel.MedicineViewModel

@Composable
fun MedicineMainScreen(viewModel: MedicineViewModel, onClick: (String) -> Unit) {
    val reminders by viewModel.allReminders.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopBar("Today's Medication")
        },
        bottomBar = {
            BottomBar()
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onClick("add")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .matchParentSize()
                        .padding(5.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = 15.dp,
                    end = 15.dp,
                )
                .verticalScroll(rememberScrollState())
        ) {
            reminders.forEach { reminder ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            viewModel.selectedReminder.value = reminder
                            onClick("details")
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color.LightGray.copy(alpha = 0.5f))
                                .weight(0.15f)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.bar_medicine),
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(vertical = 5.dp)
                                .weight(0.7f),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = reminder.medicineName,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = reminder.dosageInformation,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray.copy(alpha = 0.7f)
                                )
                            )
                        }
                        Switch(
                            checked = reminder.enabled,
                            onCheckedChange = {
                                viewModel.update(reminder.copy(enabled = it))
                            },
                            modifier = Modifier.weight(0.15f)
                        )
                    }
                }
            }
        }
    }
}