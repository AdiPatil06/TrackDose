package com.app.trackdosekotlin.shared

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class TDDataStore(context: Context) {

    private val TAG = "PreferenceDataStore"

    private val pref = context.dataStore

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("pref")
    }

    suspend fun setData(key: Preferences.Key<String>, id :String) {
        pref.edit {
            it[key] = id
        }
    }

    fun getData(key: Preferences.Key<String>) = pref.data.map {
        it[key]
    }
}