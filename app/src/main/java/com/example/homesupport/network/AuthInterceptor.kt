package com.example.homesupport.network

import com.example.homesupport.data.local.DataStoreManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest=chain.request()

        val token = runBlocking{
            dataStoreManager.getToken().first()

        }
        val requestBuilder = originalRequest.newBuilder()
        if(token!=null){
            android.util.Log.d(
                "AUTH_INTERCEPTOR",
                "Sending token: $token"
            )
            requestBuilder.header(
                "Authorization",
                "Bearer $token"
            )
        }


        val newrequest = requestBuilder.build()
        return chain.proceed(newrequest);

    }
}