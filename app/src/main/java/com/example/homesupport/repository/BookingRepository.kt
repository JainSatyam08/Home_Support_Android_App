package com.example.homesupport.repository

import android.util.Log
import com.example.homesupport.dto.BookingRequest
import com.example.homesupport.dto.BookingResponse
import com.example.homesupport.network.AuthApi
import jakarta.inject.Inject
import retrofit2.Response

class BookingRepository @Inject constructor(
    private val api: AuthApi) {
    suspend fun createBooking(request: BookingRequest): Response<BookingResponse> {

        return api.createBooking(request)

    }
}