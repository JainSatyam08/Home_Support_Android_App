package com.example.homesupport.network


import com.example.homesupport.dto.AllServiceResponse
import com.example.homesupport.dto.ApiResponse
import com.example.homesupport.dto.BookingRequest
import com.example.homesupport.dto.BookingResponse
import com.example.homesupport.dto.LoginRequest
import com.example.homesupport.dto.LoginResponse
import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.dto.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("api/users/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): Response<ApiResponse>

    @POST("api/users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("api/booking/create")
    suspend fun createBooking(
        @Body request: BookingRequest
    ): Response<BookingResponse>

    @GET("api/allService/requests")
    suspend fun getAllServiceRequests(): List<AllServiceResponse>

    @GET("api/allService/request-detail/{bookingId}")
    suspend fun getServiceDetails(
        @Path("bookingId") bookingId: String
    ): Response<ServiceDetailResponse>
}

