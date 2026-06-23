package com.example.homesupport.dto


data class SignupRequest(
    val fullName: String,
    val email: String,
    val phone: String,
    val passwordHash: String
)
