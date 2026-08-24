package com.example.homesupport.repository

import com.example.homesupport.dto.CancelRequest
import com.example.homesupport.dto.CancelResponseDTO
import com.example.homesupport.network.AuthApi
import jakarta.inject.Inject
import retrofit2.Response

class CancelRequestRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun cancelBooking(bookingId: String,request: CancelRequest): Response<CancelResponseDTO>{
        return api.cancelBooking(bookingId=bookingId,request=request)
    }

}