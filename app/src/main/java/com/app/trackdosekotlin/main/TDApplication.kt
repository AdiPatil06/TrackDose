package com.app.trackdosekotlin.main

import android.app.Application
import androidx.room.Room
import com.app.trackdosekotlin.integrations.room.AppDatabase
import com.app.trackdosekotlin.shared.SharedViewModel
import com.app.trackdosekotlin.shared.TDDataStore

lateinit var app: TDApplication

class TDApplication : Application() {

    val preferenceDataStore: TDDataStore by lazy {
        TDDataStore(app.applicationContext)
    }

    val sharedViewModel by lazy {
        SharedViewModel()
    }

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        app = this

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "medicine_reminder_db"
        ).build()
    }

}