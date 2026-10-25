package com.example.homesupport.network

import com.example.homesupport.EMPLOYEE.network.EmployeeAuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance{
    private const val BASE_URL = "http://192.168.1.6:8080/"

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            //.create(AuthApi::class.java)
    }

    val api: AuthApi by lazy{
        retrofit.create(AuthApi::class.java)
    }
    val employeeApi: EmployeeAuthApi by lazy{
        retrofit.create(EmployeeAuthApi::class.java)
    }

}