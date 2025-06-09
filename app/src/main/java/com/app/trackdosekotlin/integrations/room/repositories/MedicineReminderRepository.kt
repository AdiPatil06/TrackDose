package com.app.trackdosekotlin.integrations.room.repositories

import com.app.trackdosekotlin.integrations.room.MedicineReminder
import com.app.trackdosekotlin.integrations.room.MedicineReminderDao
import kotlinx.coroutines.flow.Flow

class MedicineReminderRepository(private val dao: MedicineReminderDao) {

    val allReminders: Flow<List<MedicineReminder>> = dao.getAllReminders()

    suspend fun insert(reminder: MedicineReminder) = dao.insertReminder(reminder)

    suspend fun update(reminder: MedicineReminder) = dao.updateReminder(reminder)

    suspend fun delete(reminder: MedicineReminder) = dao.deleteReminder(reminder)
}
