package com.example.homesupport.EMPLOYEE.repository

import android.util.Log
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardCountStatsResponse
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardRecentServiceResponse
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardServiceStatsResponse
import javax.inject.Inject
import com.example.homesupport.EMPLOYEE.network.EmployeeAuthApi

class DashBoardRepository @Inject constructor(
    private val employeeAuthApi: EmployeeAuthApi
){

    suspend fun fetchDashboardStats(
        days: Int
    ): DashBoardCountStatsResponse {

        val response = employeeAuthApi.getDashBoardStats(days)

        if (!response.isSuccessful) {
            throw Exception("Dashboard API failed: ${response.code()}")
        }

        return response.body()
            ?: throw Exception("Dashboard response is empty")
    }
    suspend fun fetchCategoryStats(): DashBoardServiceStatsResponse {

        val response = employeeAuthApi.getCategoryStats()

        if (!response.isSuccessful) {
            throw Exception("Category API failed: ${response.code()}")
        }

        return response.body()
            ?: throw Exception("Category response is empty")
    }

    suspend fun fetchRecentComplaints(): List<DashBoardRecentServiceResponse> {

        val response = employeeAuthApi.getRecentComplaints()

        Log.d("DETAIL_API", "HTTP Code = ${response.code()}")
        Log.d("DETAIL_API", "Is Successful = ${response.isSuccessful}")

        if (!response.isSuccessful) {
            val error = response.errorBody()?.string()
            Log.e("DETAIL_API", "Error Body = $error")

            throw Exception("Recent complaints API failed: ${response.code()}")
        }

        val body = response.body()

        Log.d("DETAIL_API", "Response Body = $body")

        if (!response.isSuccessful) {
            throw Exception("Recent complaints API failed: ${response.code()}")
        }

        return response.body()
            ?: throw Exception("Recent complaints response is empty")
    }
}