package com.example.homesupport.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlot
import com.example.homesupport.dto.BookingRequest
import com.example.homesupport.dto.BookingResponse
import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.location.LocationProvider
import com.example.homesupport.location.LocationResult
import com.example.homesupport.repository.BookingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.String
@HiltViewModel
class BookingViewModel @Inject constructor(
    private val bookingRepository: BookingRepository,
    private val locationProvider: LocationProvider
): ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var bookingResponse by mutableStateOf<BookingResponse?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set


    var serviceType by mutableStateOf("")
        private set

    var problemDescription by mutableStateOf("")
        private set
    var problemType by mutableStateOf("")
        private set

    var selectDate by mutableStateOf(LocalDate.now())
        private set


    var selectSlot by mutableStateOf<TimeSlot?>(null)
        private set

    var selectMediaUris by mutableStateOf<List<Uri>>(emptyList())
        private set

    var latitude by mutableStateOf<Double?>(null)
        private set
    var longitude by mutableStateOf<Double?>(null)
        private set

    var address by mutableStateOf<String?>(null)
        private set



    fun updateProblemType(value: String) {
        problemType = value
    }
    fun updateaddress(value: String) {
        address = value
    }

    fun updateServiceType(service: String) {
        serviceType = service
    }

    fun updateProblemDescription(value: String) {
        problemDescription = value
    }

    fun updateSelectDate(date: LocalDate) {
        selectDate = date
    }

    fun updateSelectSlot(slot : TimeSlot) {
        selectSlot = slot
    }

    //function to add single image from gallery basically it is use for  camera
    fun addMedia(uri: Uri){
        selectMediaUris=selectMediaUris+uri
    }

    //function to add multiple image from gallery basically it is use for gallery
    fun addMedia(uris: List<Uri>){
        selectMediaUris=selectMediaUris+uris
    }

    //here both function is of same name so here we use the concept of function overloading
    //function name can be same but parameters should be different

    //function to remove image from selection
    fun removeMedia(uri: Uri) {
        selectMediaUris=selectMediaUris-uri
    }


    fun clearError() {
        errorMessage = null
    }
    fun clearBookingState() {
        bookingResponse = null

        serviceType = ""
        problemType = ""
        problemDescription = ""

        selectDate = LocalDate.now()
        selectSlot = null

        address = null
        latitude = null
        longitude = null

        selectMediaUris = emptyList()
    }
    fun bookingRequest(){
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
            val addr = freshLocation.address


            latitude = lat
            longitude = lng
            address = addr
            Log.d(
                "BOOKING_LOCATION",
                "lat=$latitude, lng=$longitude"
            )

            val slot = selectSlot ?: run {
                errorMessage = "Please select a time slot"
                return@launch
            }

            isLoading = true
            try{ val request = BookingRequest(
                serviceType = serviceType,
                problemType = problemType,
                problemDesc = problemDescription,
                preferredDate = selectDate.toString(),
                preferredSlot = slot.timeRange,
                latitude = lat,
                longitude = lng,
                addressComplete = addr
            )
                val response = bookingRepository.createBooking(request)
                if (response.isSuccessful) {
                    bookingResponse = response.body()
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
            catch(e: Exception) {


                    Log.e("BOOKING_API", "Error", e)
                    errorMessage = e.message ?: "Unknown Error"

            }

            finally {

                isLoading = false
            }
        }

    }
    fun prepareForRebooking(request: ServiceDetailResponse) {
        Log.d(
            "BOOK_AGAIN",
            "lat=${request.latitude}, lng=${request.longitude}"
        )
        serviceType = request.serviceType
        problemType = request.problemType
        problemDescription = request.problemDesc

        address = request.Address
        latitude = request.latitude
        longitude = request.longitude

        // New booking ke liye
        //selectDate = LocalDate.now()
        //selectSlot = null

    }





}