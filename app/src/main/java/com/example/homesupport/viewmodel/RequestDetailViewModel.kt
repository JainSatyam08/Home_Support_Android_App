package com.example.homesupport.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.dto.AllServiceResponse
import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.repository.ServiceDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RequestDetailViewModel @Inject constructor(
    private val serviceDetailRepository: ServiceDetailRepository
): ViewModel() {
    var requestDetail by mutableStateOf<ServiceDetailResponse?>(null)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var isLoading by mutableStateOf(false)
        private set
    fun getRequestDetail(bookingId: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val response = serviceDetailRepository.getServiceDetails(bookingId)
                if (response.isSuccessful) {
                    requestDetail = response.body()


                    Log.d("DETAIL_API", "Code = ${response.code()}")
                    Log.d("DETAIL_API", "Body = $requestDetail")
                    Log.d("DETAIL_API", "Status = ${requestDetail?.Status}")




                } else {
                    errorMessage = "Error : ${response.code()}"
                    Log.d("DETAIL_API", "Code = ${response.code()}")
                    Log.d("DETAIL_API", "Error = ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

}