package com.example.homesupport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.data.local.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager

): ViewModel() {
    var logoutSuccess by mutableStateOf(false)
        private set

    fun logout() {
        viewModelScope.launch {
            dataStoreManager.clearToken()
            logoutSuccess = true
        }
    }
}