package com.wooze.wear.woodfish.presentation.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val COUNT = intPreferencesKey("count")
val TEXT = stringPreferencesKey("text")
val COLOR = intPreferencesKey("color")
val SOUND = stringPreferencesKey("sound")
val VIBRATE = booleanPreferencesKey("vibrate")

class DataStoreManager(private val context: Context) {


    suspend fun <T> writeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

    private fun <T> readData(key: Preferences.Key<T>, default: T): Flow<T> {
        return context.dataStore.data.map { settings -> settings[key] ?: default }
    }

    suspend fun saveText(data: String) {
        writeData(TEXT, data)
    }

    suspend fun saveCount(data: Int) {
        writeData(COUNT, data)
    }

    suspend fun saveColor(data: Int) {
        writeData(COLOR, data)
    }

    suspend fun saveSound(data: String) {
        writeData(SOUND, data)
    }

    suspend fun saveVibrate(boolean: Boolean) {
        writeData(VIBRATE,boolean)
    }

    val readTextFlow: Flow<String> = readData<String>(TEXT, "功德")
    val readSoundFlow: Flow<String> = readData<String>(SOUND, "清脆")
    val readColorFlow: Flow<Int> = readData<Int>(COLOR, Color.White.toArgb())
    val readCountFlow: Flow<Int> = readData<Int>(COUNT, 0)
    val readIsVibrateOpenFlow : Flow<Boolean> = readData<Boolean>(VIBRATE,true)


}