package com.app.trackdosekotlin.tabs.medicine.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.trackdosekotlin.shared.components.ReminderTimes
import com.app.trackdosekotlin.shared.components.Selector
import com.app.trackdosekotlin.shared.components.SingleTextField
import com.app.trackdosekotlin.shared.components.TextFieldAndDropDown
import com.app.trackdosekotlin.shared.components.TopBar
import com.app.trackdosekotlin.shared.dosageTypes
import com.app.trackdosekotlin.shared.durationTypes
import com.app.trackdosekotlin.shared.frequencyTypes
import com.app.trackdosekotlin.tabs.medicine.ui.viewmodel.MedicineViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddMedicineScreen(viewModel: MedicineViewModel, code: Int? = null, onBackClick: () -> Unit) {
    val context = LocalContext.current
    var isAddTimeRequired by remember { mutableStateOf(false) }
    var isDeleteTimeRequired by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.initReminder(code)
    }

    LaunchedEffect(viewModel.frequency.value) {
        when (viewModel.frequency.value) {
            "Custom" -> {
                viewModel.reminderTime.clear()
                viewModel.reminderTime.add("09:00 AM")
                isAddTimeRequired = true
                isDeleteTimeRequired = true
            }

            "Twice Daily" -> {
                viewModel.reminderTime.clear()
                viewModel.reminderTime.add("09:00 AM")
                viewModel.reminderTime.add("09:00 AM")
                isAddTimeRequired = false
                isDeleteTimeRequired = false
            }

            else -> {
                viewModel.reminderTime.clear()
                viewModel.reminderTime.add("09:00 AM")
                isAddTimeRequired = false
                isDeleteTimeRequired = false
            }
        }
    }

    Scaffold(
        topBar = {
            val title = if (code == -1) "Add Medicine Reminder" else "Edit Medicine Reminder"
            TopBar(
                title = title,
                isBackArrowRequired = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = 15.dp,
                    end = 15.dp,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SingleTextField(
                title = "Medicine Name",
                label = "Enter Medicine Name",
                labelValue = viewModel.medicineName.value,
                onLabelVChange = {
                    viewModel.medicineName.value = it
                }
            )

            TextFieldAndDropDown(
                title = "Dosage Information",
                label1 = "Quantity",
                label1Value = viewModel.dosageQuantity.value,
                label2SelectedValue = viewModel.dosageType.value,
                label2List = dosageTypes,
                onLabel1Change = {
                    viewModel.dosageQuantity.value = it
                },
                onLabel2Change = {
                    viewModel.dosageType.value = it
                }
            )

            Selector(
                title = "How Often?",
                selected = viewModel.frequency.value,
                list = frequencyTypes,
                onClick = {
                    viewModel.frequency.value = it
                }
            )

            ReminderTimes(
                title = "Reminder Times",
                list = viewModel.reminderTime,
                isAddRequired = isAddTimeRequired,
                isDeleteRequired = isDeleteTimeRequired,
                onSelect = { index, time ->
                    viewModel.reminderTime[index] = time
                },
                onAdd = { time ->
                    viewModel.reminderTime.add(time)
                },
                onDelete = {
                    viewModel.reminderTime.remove(it)
                }
            )

            TextFieldAndDropDown(
                title = "Duration",
                label1 = "Number",
                label1Value = viewModel.duration.value,
                label2SelectedValue = viewModel.durationType.value,
                label2List = durationTypes,
                onLabel1Change = {
                    viewModel.duration.value = it
                },
                onLabel2Change = {
                    viewModel.durationType.value = it
                }
            )

            SingleTextField(
                title = "Additional Notes",
                label = "",
                labelValue = viewModel.additionalNotes.value,
                onLabelVChange = {
                    viewModel.additionalNotes.value = it
                }
            )

            Button(
                onClick = {
                    viewModel.saveReminder(code) {
                        Toast.makeText(context, "Reminder saved!", Toast.LENGTH_SHORT).show()
                        onBackClick()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    "Save Reminder",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    ),
                )
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}