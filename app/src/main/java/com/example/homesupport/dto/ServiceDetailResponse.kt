package com.example.homesupport.dto

data class ServiceDetailResponse (
    val CustomerAddress: String,
    val CustomerEmail: String,
    val CustomerForCno: String,
    val CustomerForName: String,
    val status: String,
    val bookingDate: String,
    val bookingId: String,
    val prefDate: String,
    val prefSlot: String,
    val problemDesc: String,
    val problemType: String,
    val serviceType: String,
    val statusUpdatedAt: String
)
