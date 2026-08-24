package com.example.homesupport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.dto.CancelRequest
import com.example.homesupport.dto.CancelResponseDTO
import com.example.homesupport.location.LocationProvider
import com.example.homesupport.location.LocationResult
import com.example.homesupport.repository.CancelRequestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._com_example_homesupport_viewmodel_BookingViewModel_HiltModules_KeyModule
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CancelRequestViewModel @Inject constructor(
    private val cancelRequestRepository: CancelRequestRepository,
    private val locationProvider: LocationProvider
): ViewModel(){

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private  set
    var cancelResponse by mutableStateOf<CancelResponseDTO?>(null)
        private set

    var reason by mutableStateOf("")
        private set

    var latitude by mutableStateOf<Double?>(null)
        private set
    var longitude by mutableStateOf<Double?>(null)
        private set

    fun updateReason(value: String) {
        reason = value
    }

    fun cancelRequest(bookingId:String){
        errorMessage=null
        viewModelScope.launch {
            val locationResult = locationProvider.getFreshLocation()

            if (locationResult !is LocationResult.Success) {
                errorMessage = "Unable to fetch current location"
                return@launch
            }

            val freshLocation = locationResult.location

            val lat = freshLocation.latitude
            val lng = freshLocation.longitude



            latitude = lat
            longitude = lng

            val reason = reason ?: run {
                errorMessage = "Please specify the reason"
                return@launch
            }

            isLoading=true
            try{ val request= CancelRequest(
                reason=reason,
                latitude=lat,
                longitude=lng
            )
                val response = cancelRequestRepository.cancelBooking(bookingId,request)
                if (response.isSuccessful) {
                    cancelResponse = response.body()
                } else {
                    when(response.code()){
                        400 -> errorMessage = "Invalid Booking Details"
                        401 -> errorMessage = "Session Expired. Please login again."
                        403 -> errorMessage = "You are not authorized."
                        404 -> errorMessage = "Resource Not Found"
                        500 -> errorMessage = "Server Error. Please try again."
                        else-> errorMessage = "Something Went Wrong."
                    }

                }


            }
            catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }


        }
    }



}