package com.example.homesupport.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.homesupport.viewmodel.LoginViewModel

@Composable
fun LoginScreen(nav: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A90E2)
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = loginViewModel.phone,
            onValueChange = { loginViewModel.updatephone(it) },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.updatepassword(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(Modifier.height(20.dp))

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
                loginViewModel.validatelogin()
                if (loginViewModel.error.isEmpty()) {
                    nav.navigate("user_dashboard")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}
