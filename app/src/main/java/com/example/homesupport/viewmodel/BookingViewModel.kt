package com.example.homesupport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlot
import java.time.LocalDate

class BookingViewModel : ViewModel() {


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

    fun updateProblemType(value: String) {
        problemType = value
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


}