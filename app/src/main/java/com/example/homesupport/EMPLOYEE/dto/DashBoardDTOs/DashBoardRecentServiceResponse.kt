package com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs

data class DashBoardRecentServiceResponse(
    val bookingId :String,
    val problemDesc :String,
    val roomNo :String,
    val serviceType :String,
    val timeCalculated :String,
    val location :String
)
