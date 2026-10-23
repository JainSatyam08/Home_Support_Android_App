package com.example.homesupport.EMPLOYEE.dto

data class EmployeeLoginResponse(
    val token: String,
    val role: String,
    val name:String
)
