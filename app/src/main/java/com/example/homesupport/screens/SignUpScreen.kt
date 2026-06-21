package com.example.homesupport.screens

import com.example.homesupport.components.signin.AppLogo
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.homesupport.components.signin.AlreadyHaveAccountPrompt
import com.example.homesupport.components.signin.AuthInputField
import com.example.homesupport.components.signin.PasswordField
import com.example.homesupport.components.signin.RegisterButton
import com.example.homesupport.components.signin.SignUpSubtitle
import com.example.homesupport.components.signin.SignUpTitle

import com.example.homesupport.viewmodel.LoginViewModel

@Composable
fun SignUpScreen(nav: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 32.dp)
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppLogo()

        Spacer(modifier = Modifier.height(20.dp))

        SignUpTitle()

        Spacer(modifier = Modifier.height(8.dp))

        SignUpSubtitle()

        Spacer(modifier = Modifier.height(28.dp))

        AuthInputField(
            value          = loginViewModel.fullname,
            onValueChange  = {
                loginViewModel.updatefullname(it) 
            },
            placeholder    = "Full Name",
            leadingIcon    = Icons.Outlined.Person,
            iconDescription = "Name icon",
            keyboardType   = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(14.dp))

        AuthInputField(
            value          = loginViewModel.email,
            onValueChange  = {
                loginViewModel.updateemail(it)
            },
            placeholder    = "Email Address",
            leadingIcon    = Icons.Outlined.Email,
            iconDescription = "Email icon",
            keyboardType   = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(14.dp))

        AuthInputField(
            value          = loginViewModel.phone,
            onValueChange  = {
                loginViewModel.updatephone(it)
            },
            placeholder    = "Phone Number",
            leadingIcon    = Icons.Outlined.Phone,
            iconDescription = "Phone icon",
            keyboardType   = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(14.dp))

        PasswordField(
            value          = loginViewModel.password,
            onValueChange  = {
                loginViewModel.updatepassword(it)
            },
            placeholder    = "Password"
        )

        Spacer(modifier = Modifier.height(14.dp))

        PasswordField(
            value          = loginViewModel.confirmpassword,
            onValueChange  = {
                loginViewModel.updateconfirmpassword(it)
            },
            placeholder    = "Confirm Password"
        )

        Spacer(modifier = Modifier.height(28.dp))
        if (loginViewModel.error.isNotEmpty()) {
            Text(
                text = loginViewModel.error,
                color = Color.Red
            )
        }

        RegisterButton(
            onClick = {
                loginViewModel.validatesignup()
                if (loginViewModel.error.isEmpty()) {
                    nav.navigate("login")
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        AlreadyHaveAccountPrompt(nav)
    }
}

