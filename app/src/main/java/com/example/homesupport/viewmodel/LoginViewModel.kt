package com.example.homesupport.viewmodel


import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var error by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set
    fun updatephone(phone: String){
        this.phone = phone
    }
    fun updatepassword(password:String){
        this.password = password
    }

    fun validatelogin(/*phone: String, password: String*/) { //because viewmodel mai hi parameter hai baar baar lene ki zarurat nhi hai}

        if(phone.isEmpty() || password.isEmpty()){
            error = "Please fill all fields"
        }
        else{
            error = ""
        }
    }




}