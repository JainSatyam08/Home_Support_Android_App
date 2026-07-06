package com.example.homesupport.network


import com.example.homesupport.dto.ApiResponse
import com.example.homesupport.dto.LoginRequest
import com.example.homesupport.dto.LoginResponse
import com.example.homesupport.dto.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface AuthApi {
    @POST("api/users/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): Response<ApiResponse>

    @POST("api/users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

}