package com.example.homesupport.viewmodel


import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.dto.ApiResponse
import com.example.homesupport.dto.LoginRequest
import com.example.homesupport.repository.AuthRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    //private val repository = AuthRepository()

    var loginSuccess by mutableStateOf(false)
        private set

    var error by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set
    fun updateemail(email: String){
        this.email = email
    }
    fun updatepassword(password:String){
        this.password = password
    }
    fun validatelogin(/*phone: String, password: String*/) { //because viewmodel mai hi parameter hai baar baar lene ki zarurat nhi hai}

        if(email.isEmpty() || password.isEmpty()){
            error = "Please fill all fields"
        }
        else{
            error = ""
        }
    }
    fun login(){
        viewModelScope.launch {
            try{
                val request= LoginRequest(
                    email=email,
                    password=password
                )
                val response = repository.login(request)

                if(response.isSuccessful){
                    val token=response.body()?.token
                    android.util.Log.d("JWT",token?:"No Token")
                    loginSuccess = true
                }
                else{
                    val errorJson = response.errorBody()?.string()

                    val apiError = Gson().fromJson(
                        errorJson,
                        ApiResponse::class.java
                    )

                    error = apiError.message
                }

            }
            catch (e: Exception){
                error = "Network Error: ${e.message}"
            }
        }
    }




}