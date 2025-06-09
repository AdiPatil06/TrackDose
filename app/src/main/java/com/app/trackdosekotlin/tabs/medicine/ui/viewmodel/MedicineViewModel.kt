package com.app.trackdosekotlin.tabs.medicine.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trackdosekotlin.integrations.room.MedicineReminder
import com.app.trackdosekotlin.integrations.room.repositories.MedicineReminderRepository
import com.app.trackdosekotlin.main.app
import com.app.trackdosekotlin.shared.dosageTypes
import com.app.trackdosekotlin.shared.durationTypes
import com.app.trackdosekotlin.shared.frequencyTypes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MedicineViewModel : ViewModel() {

    private val TAG = "MedicineViewModel"

    private val dao = app.database.medicineReminderDao()
    private val repository = MedicineReminderRepository(dao)

    var medicineName = mutableStateOf("")
    var dosageQuantity = mutableStateOf("")
    var dosageType = mutableStateOf(dosageTypes.first())
    var frequency = mutableStateOf(frequencyTypes.first())
    var duration = mutableStateOf("")
    var durationType = mutableStateOf(durationTypes.first())
    val reminderTime = mutableStateListOf<String>("09:00 AM")
    var additionalNotes = mutableStateOf("")

    var selectedReminder = mutableStateOf<MedicineReminder?>(null)

    var allReminders = repository.allReminders

    fun initReminder(code: Int?) {
        viewModelScope.launch {
            if (code == -1) {
                medicineName.value = ""
                dosageQuantity.value = ""
                durationType.value = ""
                frequency.value = ""
                duration.value = ""
                durationType.value = durationTypes.first()
                reminderTime.clear()
                reminderTime.add("09:00 AM")
                additionalNotes.value = ""
            } else {
                allReminders.collectLatest {
                    val reminder = it.find { it.id == code }
                    medicineName.value = reminder?.medicineName ?: ""
                    dosageQuantity.value = reminder?.dosageInformation?.split(" ")?.first() ?: ""
                    dosageType.value = reminder?.dosageInformation?.split(" ")[1] ?: ""
                    frequency.value = reminder?.frequency ?: ""
                    duration.value = reminder?.duration?.split(" ")?.first() ?: ""
                    durationType.value = reminder?.duration?.split(" ")[1] ?: ""
                    reminderTime.clear()
                    reminderTime.addAll(reminder?.reminderTimes ?: emptyList())
                    additionalNotes.value = reminder?.additionalNotes ?: ""
                }
            }
        }
    }

    fun saveReminder(code: Int?, onComplete: () -> Unit) {
        Log.i(TAG, "saveReminder: ")
        viewModelScope.launch {
            if (code == -1) {
                Log.i(TAG, "saveReminder: insert")
                val reminder = MedicineReminder(
                    medicineName = medicineName.value.trim(),
                    dosageInformation = "${dosageQuantity.value.trim()} ${dosageType.value.trim()}",
                    frequency = frequency.value.trim(),
                    reminderTimes = reminderTime,
                    duration = "${duration.value.trim()} ${durationType.value.trim()}",
                    additionalNotes = additionalNotes.value.trim(),
                    enabled = true
                )
                insert(reminder, onComplete = onComplete)
            } else {
                Log.i(TAG, "saveReminder: update -> $frequency")
                val existing = allReminders.first().find { it.id == code }
                val updated = existing?.copy(
                    medicineName = medicineName.value.trim(),
                    dosageInformation = "${dosageQuantity.value.trim()} ${dosageType.value.trim()}",
                    frequency = frequency.value.trim(),
                    reminderTimes = reminderTime,
                    duration = "${duration.value.trim()} ${durationType.value.trim()}",
                    additionalNotes = additionalNotes.value.trim(),
                    enabled = true
                )
                updated?.let { update(it, onComplete) }
            }
        }
    }

    fun insert(reminder: MedicineReminder, onComplete: () -> Unit = {}) = viewModelScope.launch {
        Log.i(TAG, "insert: $reminder")
        repository.insert(reminder)
        onComplete()
    }

    fun update(reminder: MedicineReminder, onComplete: () -> Unit = {}) = viewModelScope.launch {
        Log.i(TAG, "update: $reminder")
        repository.update(reminder)
        onComplete()
    }

    fun delete(reminder: MedicineReminder, onComplete: () -> Unit = {}) = viewModelScope.launch {
        Log.i(TAG, "delete: $reminder")
        repository.delete(reminder)
        onComplete()
    }

}