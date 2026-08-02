package com.example.homesupport.repository

import com.example.homesupport.dto.AllServiceResponse
import com.example.homesupport.network.AuthApi
import javax.inject.Inject

class AllServiceRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun getAllServiceRequests() : List<AllServiceResponse> {
        return api.getAllServiceRequests()
    }
}