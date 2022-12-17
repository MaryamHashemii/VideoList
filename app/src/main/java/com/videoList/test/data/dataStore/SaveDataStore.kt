package com.videoList.test.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("data")

class SaveDataStore @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    suspend fun save(key: String, value: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    suspend fun get(key: String): Boolean {
        return dataStore.data.first()[booleanPreferencesKey(key)] ?: false
    }

}