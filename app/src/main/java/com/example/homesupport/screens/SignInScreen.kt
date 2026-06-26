package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.homesupport.components.signin.AppLogo
import com.example.homesupport.components.signin.ContinueButton
import com.example.homesupport.components.signin.EmailInputField
import com.example.homesupport.components.signin.PasswordInputField
import com.example.homesupport.components.signin.SignInTitle
import com.example.homesupport.components.signin.SignUpPrompt
import com.example.homesupport.components.signin.SocialLoginRow
import com.example.homesupport.viewmodel.LoginViewModel


@Composable
fun SignInScreen(nav: NavHostController

) {

    val loginViewModel: LoginViewModel = viewModel()
    LaunchedEffect(loginViewModel.loginSuccess) {
        if(loginViewModel.loginSuccess){
            nav.navigate("user_dashboard"){
                popUpTo("login"){
                    inclusive = true
                }
            }

        }


    }
    Column(
        modifier = Modifier
            .fillMaxSize()                      // Poori screen cover karo
            .background(Color.White)            // White background
            .padding(horizontal = 32.dp),       // Left-Right padding — content ko edges se door rakhta hai
        horizontalAlignment = Alignment.CenterHorizontally, // Sab items horizontally center mein
        verticalArrangement = Arrangement.Center            // Sab items vertically screen ke beech mein
    ) {


        AppLogo()

        Spacer(modifier = Modifier.height(24.dp)) // Logo aur title ke beech gap


        SignInTitle()

        Spacer(modifier = Modifier.height(32.dp)) // Title aur first input ke beech thoda zyada gap


        EmailInputField(
            value = loginViewModel.email,                      // Current value dikhao
            onValueChange = { loginViewModel.updateemail(it) }      // User type kare toh state update karo
        )

        Spacer(modifier = Modifier.height(14.dp)) // Email aur password field ke beech gap

        // ── 4. Password input field ────────────────────────────────────────
        PasswordInputField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.updatepassword(it) }
        )
        if (loginViewModel.error.isNotEmpty()) {
            Text(
                text = loginViewModel.error,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(12.dp)) // Password field aur links ke beech gap

        // ── 5. Forgot Password + Use Phone links ───────────────────────────
        /*ForgotPasswordLinks(
            onForgotPassword = onForgotPassword,
            onUsePhone = onUsePhone
        )*/

        Spacer(modifier = Modifier.height(24.dp)) // Links aur Continue button ke beech gap

        // ── 6. Green "Continue" button ─────────────────────────────────────
        // Email aur password dono is lambda mein pass ho rahe hain
        ContinueButton(
            onClick = {
                loginViewModel.validatelogin()
                if (loginViewModel.error.isEmpty()) {
                    loginViewModel.login()
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp)) // Button aur social icons ke beech gap

        // ── 7. Google + Phone social login buttons ─────────────────────────
        SocialLoginRow(
            //onGoogleClick = onGoogleSignIn,
            //onPhoneClick  = onPhoneSignIn
        )

        Spacer(modifier = Modifier.height(24.dp)) // Social icons aur signup prompt ke beech gap

        // ── 8. "New to the app? Sign Up" prompt ───────────────────────────
        SignUpPrompt(nav)
    }
}









/*
@Composable
fun ForgotPasswordLinks(
    onForgotPassword: () -> Unit,
    onUsePhone: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // Dono links center mein
    ) {
        // Link 1: Forgot Password
        Text(
            text = "Forgot Password?",
            color = LinkBlue,                               // Blue = clickable indicate karta hai
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { onForgotPassword() } // Click hone pe callback
        )

        Spacer(modifier = Modifier.height(4.dp))           // Dono links ke beech chhota gap

        // Link 2: Use Phone Number
        Text(
            text = "Use phone number instead",
            color = LinkBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { onUsePhone() } // Click hone pe callback
        )
    }
}
*/