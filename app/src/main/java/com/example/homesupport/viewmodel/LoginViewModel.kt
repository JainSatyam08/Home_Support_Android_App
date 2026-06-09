package com.example.homesupport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var error by  mutableStateOf("")
        private set

    fun validatelogin(phone: String,password: String){
        if(phone.isEmpty() || password.isEmpty()){
            error="Please fill all fields"
        }
        else{
            error=""
        }
    }


}