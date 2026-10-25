package com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs

data class DashBoardCountStatsResponse(
    val total: Long?,
    val newcount: Long?,
    val reviewed: Long?,
    val completed: Long?,
    val cancelled: Long?
)
