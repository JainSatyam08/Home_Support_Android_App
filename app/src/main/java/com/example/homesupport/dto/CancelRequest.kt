package com.example.homesupport.dto

data class CancelRequest(
    val reason: String,
    val latitude: Double,
    val longitude: Double

)