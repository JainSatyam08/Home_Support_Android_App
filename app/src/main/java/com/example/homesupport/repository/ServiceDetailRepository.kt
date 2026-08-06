package com.example.homesupport.repository

import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.network.AuthApi
import javax.inject.Inject

class ServiceDetailRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun getServiceDetail(bookingId: String): ServiceDetailResponse {
        return api.getServiceDetail(bookingId)
    }
}