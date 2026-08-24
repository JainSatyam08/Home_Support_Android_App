package com.example.homesupport.dto

data class ServiceDetailResponse (
    val Address: String,
    val CustomerEmail: String,
    val CustomerForCno: String,
    val CustomerForName: String,
    val Status: String,
    val bookingDate: String,
    val bookingId: String,
    val prefDate: String,
    val prefSlot: String,
    val problemDesc: String,
    val problemType: String,
    val serviceType: String,
    val statusUpdatedAt: String,
    val latitude: Double,
    val longitude: Double
)
