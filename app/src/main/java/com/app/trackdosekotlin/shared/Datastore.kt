package com.app.trackdosekotlin.shared

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class TDDataStore(context: Context) {

    private val TAG = "PreferenceDataStore"

    private val pref = context.dataStore

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("pref")

        val splashScreen = booleanPreferencesKey("SPLASH_SCREEN")
    }

    suspend fun setDataString(key: Preferences.Key<String>, id: String) {
        pref.edit {
            it[key] = id
        }
    }

    fun getDataString(key: Preferences.Key<String>) = pref.data.map {
        it[key]
    }

    suspend fun setDataBoolean(key: Preferences.Key<Boolean>, id: Boolean) {
        pref.edit {
            it[key] = id
        }
    }

    fun getDataBoolean(key: Preferences.Key<Boolean>) = pref.data.map {
        it[key]
    }
}