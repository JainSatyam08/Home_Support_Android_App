package com.example.homesupport.dto

class CancelResponseDTO(
    val cancelId: String,
    val bookingId: String,
    val cancelled_by: String,
    val reason: String,
    val serviceType: String,

)
