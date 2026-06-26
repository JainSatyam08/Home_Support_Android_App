package com.example.homesupport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.dto.SignupRequest
import kotlinx.coroutines.launch
import com.example.homesupport.repository.AuthRepository

class SignupViewModel : ViewModel() {
    private val repository = AuthRepository()
    var error by mutableStateOf("")
        private set

    var signupsucces by mutableStateOf(false)
        private set

    var phone by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var fullname by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set

    fun updatefullname(fullname: String){
        this.fullname = fullname
    }
    var confirmpassword by mutableStateOf("")
        private set


    fun updateemail(email: String){
        this.email = email
    }



    fun updateconfirmpassword(confirmpassword: String){
        this.confirmpassword = confirmpassword
    }




    fun updatephone(phone: String){
        this.phone = phone
    }
    fun updatepassword(password:String){
        this.password = password
    }


    fun validatesignup(){
        if(fullname.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() ){
            error = "Please fill all fields"
        }
        else if(password != confirmpassword){
            error="Password does not match."
        }
        else if(phone.trimmedLength()!=10){
            error="Phone Number is invalid."
        }
        else{
            error = ""
        }


    }
    fun signup(){
        viewModelScope.launch {
            try {
                val request = SignupRequest(
                    fullName = fullname,
                    email = email,
                    phone = phone,
                    passwordHash = password

                )
                val response = repository.signup(request)
                if (response.isSuccessful) {
                    error = response.body()?.message ?:"Signup Success"
                    signupsucces = true

                } else {
                    error = response.errorBody()?.string() ?:"Signup Failed"

                }


            } catch (e: Exception) {
                error = "Network Error: ${e.message}"
            }
        }
    }


}