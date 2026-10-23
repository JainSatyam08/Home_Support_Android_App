package com.example.homesupport.EMPLOYEE.network

import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginRequest
import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginResponse
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface EmployeeAuthApi {
    @POST("auth/employee/login")
    suspend fun login(
        @Body request: EmployeeLoginRequest
    ): Response<EmployeeLoginResponse>
}