package com.example.homesupport.dto

import java.time.LocalDate

data class AllServiceResponse(

    val bookingId: String,
    val trackId: Long,
    val status: String,
    val serviceType: String,
    val statusUpdatedAt: String
)
