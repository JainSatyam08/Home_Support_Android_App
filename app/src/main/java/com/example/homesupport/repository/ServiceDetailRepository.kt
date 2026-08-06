package com.example.homesupport.repository

import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.network.AuthApi

import javax.inject.Inject
import retrofit2.Response
class ServiceDetailRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun getServiceDetails(bookingId: String): Response<ServiceDetailResponse> {
        return api.getServiceDetails(bookingId)
    }
}