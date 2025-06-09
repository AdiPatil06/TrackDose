package com.app.trackdosekotlin.integrations.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_reminders")
data class MedicineReminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medicineName: String,
    val dosageInformation: String,
    val frequency: String,
    val reminderTimes: List<String>,
    val duration: String,
    val additionalNotes: String,
    val enabled: Boolean
)
