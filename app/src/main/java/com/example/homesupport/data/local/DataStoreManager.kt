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
    private val ACCOUNT_TYPE_KEY = stringPreferencesKey("account_type")


    suspend fun saveToken(token: String) {
        context.dataStore.edit{ preferences ->
            preferences [TOKEN_KEY]=token
        }

    }
    suspend fun saveSession(token: String, accountType: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[ACCOUNT_TYPE_KEY] = accountType
        }
    }
    fun getAccountType() = context.dataStore.data.map { preferences ->
        preferences[ACCOUNT_TYPE_KEY]
    }

    fun getToken()= context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]

    }
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(ACCOUNT_TYPE_KEY)
        }
    }

}