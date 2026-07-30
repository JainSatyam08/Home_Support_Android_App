package com.example.homesupport.repository

import android.content.Context
import com.example.homesupport.data.local.DataStoreManager
import com.example.homesupport.dto.ApiResponse
import com.example.homesupport.dto.LoginRequest
import com.example.homesupport.dto.LoginResponse
import com.example.homesupport.dto.SignupRequest
import com.example.homesupport.network.RetrofitInstance

import com.example.homesupport.network.AuthApi
import jakarta.inject.Inject
import retrofit2.Response

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val dataStoreManager: DataStoreManager

) {
    //private val dataStoreManager = DataStoreManager(context);
    suspend fun signup(request: SignupRequest): Response<ApiResponse> {
        return api.signup(request)
    }
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        val response= api.login(request)
        if(response.isSuccessful){
            val token = response.body()?.token?.let { token ->
                dataStoreManager.saveToken(token)
            }
        }
        return response;
    }
    fun getToken() = dataStoreManager.getToken()
    suspend fun logout() {
        dataStoreManager.clearToken()
    }

}