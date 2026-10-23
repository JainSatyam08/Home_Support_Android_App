package com.example.homesupport.EMPLOYEE.repository

import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginRequest
import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginResponse
import com.example.homesupport.EMPLOYEE.network.EmployeeAuthApi
import com.example.homesupport.data.local.DataStoreManager
import com.example.homesupport.dto.LoginRequest
import com.example.homesupport.dto.LoginResponse
import jakarta.inject.Inject
import retrofit2.Response

class EmployeeAuthRepository @Inject constructor(
    private val employeeAuthApi: EmployeeAuthApi,
    private val dataStoreManager: DataStoreManager
) {
    suspend fun login(request: EmployeeLoginRequest): Response<EmployeeLoginResponse> {
        val response= employeeAuthApi.login(request)
        if(response.isSuccessful){
            val token = response.body()?.token?.let { token ->
                dataStoreManager.saveSession(token,"EMPLOYEE")
            }
        }
        return response;
    }
}