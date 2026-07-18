package com.example.homesupport.data.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
private const val DATASTORE_NAME="home_support_app"
val Context.dataStore by preferencesDataStore(
    name = DATASTORE_NAME
)