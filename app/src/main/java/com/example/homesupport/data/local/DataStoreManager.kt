package com.example.homesupport.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.map
import kotlin.text.set

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val TOKEN_KEY= stringPreferencesKey("token")

    suspend fun saveToken(token: String) {
        context.dataStore.edit{ preferences ->
            preferences [TOKEN_KEY]=token
        }

    }

    fun getToken()= context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]

    }

}