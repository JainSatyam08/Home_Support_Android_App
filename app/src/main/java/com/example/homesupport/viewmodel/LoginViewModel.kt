package com.example.homesupport.viewmodel


import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var error by mutableStateOf("")
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

    fun updateconfirmpassword(confirmpassword: String){
        this.confirmpassword = confirmpassword
    }




    fun updatephone(phone: String){
        this.phone = phone
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




}