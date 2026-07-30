package com.example.homesupport.dto

data class BookingRequest(
    val serviceType: String,
    val problemType: String?,
    val problemDesc: String?,
    val preferredDate: String,
    val preferredSlot: String,
    val latitude: Double,
    val longitude: Double,
    val addressComplete: String
)
