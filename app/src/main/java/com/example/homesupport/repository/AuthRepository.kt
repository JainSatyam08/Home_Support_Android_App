package com.example.homesupport.repository

import com.example.homesupport.dto.ApiResponse
import com.example.homesupport.dto.LoginRequest
import com.example.homesupport.dto.SignupRequest
import com.example.homesupport.network.RetrofitInstance

import com.example.homesupport.network.AuthApi
import retrofit2.Response

class AuthRepository {
    suspend fun signup(request: SignupRequest): Response<ApiResponse> {
        return RetrofitInstance.api.signup(request)
    }
    suspend fun login(request: LoginRequest): Response<ApiResponse> {
        return RetrofitInstance.api.login(request)
    }

}