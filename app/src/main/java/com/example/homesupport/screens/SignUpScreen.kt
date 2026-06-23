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

import com.example.homesupport.viewmodel.SignupViewModel

@Composable
fun SignUpScreen(nav: NavHostController) {

    val signupViewModel: SignupViewModel = viewModel()
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
            value          = signupViewModel.fullname,
            onValueChange  = {
                signupViewModel.updatefullname(it)
            },
            placeholder    = "Full Name",
            leadingIcon    = Icons.Outlined.Person,
            iconDescription = "Name icon",
            keyboardType   = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(14.dp))

        AuthInputField(
            value          = signupViewModel.email,
            onValueChange  = {
                signupViewModel.updateemail(it)
            },
            placeholder    = "Email Address",
            leadingIcon    = Icons.Outlined.Email,
            iconDescription = "Email icon",
            keyboardType   = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(14.dp))

        AuthInputField(
            value          = signupViewModel.phone,
            onValueChange  = {
                signupViewModel.updatephone(it)
            },
            placeholder    = "Phone Number",
            leadingIcon    = Icons.Outlined.Phone,
            iconDescription = "Phone icon",
            keyboardType   = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(14.dp))

        PasswordField(
            value          = signupViewModel.password,
            onValueChange  = {
                signupViewModel.updatepassword(it)
            },
            placeholder    = "Password"
        )

        Spacer(modifier = Modifier.height(14.dp))

        PasswordField(
            value          = signupViewModel.confirmpassword,
            onValueChange  = {
                signupViewModel.updateconfirmpassword(it)
            },
            placeholder    = "Confirm Password"
        )

        Spacer(modifier = Modifier.height(28.dp))
        if (signupViewModel.error.isNotEmpty()) {
            Text(
                text = signupViewModel.error,
                color = Color.Red
            )
        }

        RegisterButton(
            onClick = {
                signupViewModel.validatesignup()
                if (signupViewModel.error.isEmpty()) {
                    signupViewModel.signup()
                     //nav.navigate("login")
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        AlreadyHaveAccountPrompt(nav)
    }
}

