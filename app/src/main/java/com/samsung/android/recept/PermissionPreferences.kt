package com.samsung.android.recept

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1. Create the DataStore delegate at the top level
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "permission_prefs")

class PermissionPreferences(private val context: Context) {

    // 2. Define your keys
    companion object {
        val HAS_REQUESTED_AUDIO = booleanPreferencesKey("has_requested_audio")
    }

    // 3. Read the value as a Flow
    val hasRequestedAudio: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            // Return the boolean value, defaulting to false if not set
            preferences[HAS_REQUESTED_AUDIO] ?: false
        }

    // 4. Write the value
    suspend fun setHasRequestedAudio(hasRequested: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HAS_REQUESTED_AUDIO] = hasRequested
        }
    }
}
