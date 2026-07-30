package com.example.homesupport.dto

import com.google.gson.annotations.SerializedName

data class BookingResponse(
    val message: String,
    val bookingId: String,
    val trackId: Long,
    val status: String,
    val serviceType: String,
    @SerializedName("cname")
    val customerName: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String
)
