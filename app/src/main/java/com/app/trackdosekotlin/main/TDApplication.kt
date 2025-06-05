package com.app.trackdosekotlin.main

import android.app.Application
import com.app.trackdosekotlin.shared.TDDataStore

lateinit var app: TDApplication

class TDApplication : Application() {

    val preferenceDataStore : TDDataStore by lazy {
        TDDataStore(app.applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}