package com.example.homesupport.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homesupport.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val token = authRepository.getToken()

}