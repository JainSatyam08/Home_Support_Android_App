package com.example.homesupport.screens

import  androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homesupport.viewmodel.LoginViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun LoginScreen(nav: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel()

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A90E2)

        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = phone,                    // Current value
            onValueChange = { phone = it },   // User type kare toh update
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone // Mobile keyboard
            )
        )
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = password,                    // Current value
            onValueChange = { password = it },   // User type kare toh update
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(Modifier.height(20.dp))

        // AGAR KOI ERROR MESSAGE HAI TOH DIKHAYEGA
        if (loginViewModel.error.isNotEmpty()) {
            Text(
                    loginViewModel.error,
                color = Color.Red,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(10.dp))
        }
        Button(
            onClick = {
                loginViewModel.validatelogin(phone,password)
                if(loginViewModel.error.isEmpty()){
                    nav.navigate("user_dashboard")
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }




    }


}
