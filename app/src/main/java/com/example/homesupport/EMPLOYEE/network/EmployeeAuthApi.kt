package com.example.homesupport.EMPLOYEE.network

import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardCountStatsResponse
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardRecentServiceResponse
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardServiceStatsResponse
import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginRequest
import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginResponse
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmployeeAuthApi {
    @POST("auth/employee/login")
    suspend fun login(
        @Body request: EmployeeLoginRequest
    ): Response<EmployeeLoginResponse>

    @GET("auth/employee/stats")
    suspend fun getDashBoardStats(
        @Query("days") days: Int
    ): Response<DashBoardCountStatsResponse>

    @GET("auth/employee/statservice")
    suspend fun getCategoryStats(): Response<DashBoardServiceStatsResponse>

    @GET("auth/employee/recentservice")
    suspend fun getRecentComplaints(): Response<List<DashBoardRecentServiceResponse>>


}