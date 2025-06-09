package com.app.trackdosekotlin.integrations.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: MedicineReminder)

    @Update
    suspend fun updateReminder(reminder: MedicineReminder)

    @Delete
    suspend fun deleteReminder(reminder: MedicineReminder)

    @Query("SELECT * FROM medicine_reminders ORDER BY id DESC")
    fun getAllReminders(): Flow<List<MedicineReminder>>

    @Query("SELECT * FROM medicine_reminders WHERE enabled = 1")
    fun getActiveReminders(): Flow<List<MedicineReminder>>
}
