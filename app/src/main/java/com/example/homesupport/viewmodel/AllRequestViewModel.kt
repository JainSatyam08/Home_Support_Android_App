package com.example.homesupport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.homesupport.dto.AllServiceResponse
import com.example.homesupport.repository.AllServiceRepository
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRequestViewModel @Inject constructor(
    private val allServiceRepository: AllServiceRepository
): ViewModel(){
    var allRequests by mutableStateOf<List<AllServiceResponse>>(emptyList())
        private set;
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var isLoading by mutableStateOf(false)
        private set
    fun getAllRequest(){
        viewModelScope.launch {
            isLoading = true
            errorMessage=null
            try {
                val response = allServiceRepository.getAllServiceRequests()
                allRequests = response
            }
            catch (e: Exception) {

                errorMessage = e.message ?: "Something went wrong"

            } finally {

                isLoading = false

            }
        }
    }
}