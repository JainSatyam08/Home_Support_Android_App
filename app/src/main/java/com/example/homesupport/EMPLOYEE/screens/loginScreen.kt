package com.example.homesupport.EMPLOYEE.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homesupport.EMPLOYEE.viewmodel.EmployeeLoginViewModel
import com.example.homesupport.screens.FooterG
import com.example.homesupport.screens.HeaderG

// ---------- Colors specific to this screen ----------
// (ScreenBg, NavyText, BrandBlue, GrayText are private in the first file,
//  so either remove `private` there or keep these copies here)
private val LoginScreenBg = Color(0xFFF7FAFE)
private val LoginNavy = Color(0xFF0F2A52)
private val LoginBlue = Color(0xFF2F80D8)
private val LoginGray = Color(0xFF4A5B73)
private val FieldBorder = Color(0xFFD5DEEA)
private val PlaceholderGray = Color(0xFF8A97AB)
private val ButtonTop = Color(0xFF4C9DF6)
private val ButtonBottom = Color(0xFF2F7FE0)

// ---------- Main Screen ----------
@Composable
fun EmployeeLoginScreenG(navController: NavController) {
    val viewModel: EmployeeLoginViewModel = hiltViewModel()
    val employeeId = viewModel.employeeId
    val password = viewModel.password
    val error = viewModel.error
    val loading = viewModel.loading
    val loginSuccess = viewModel.loginSuccess
    LaunchedEffect(loginSuccess, viewModel.role) {
        if (loginSuccess) {
            val destination = when (viewModel.role) {
                "Warden" -> "employeeDashBoard"
                else -> null
            }

            viewModel.resetLoginState()

            destination?.let {
                navController.navigate("$it/${viewModel.name}") {
                    launchSingleTop = true
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginScreenBg)
    ) {
        // Footer drawn first so the form sits on top of it (reused from the previous screen)
        FooterG(modifier = Modifier.align(Alignment.BottomCenter))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()) // keeps the form reachable when keyboard opens
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Logo + app name + tagline (reused)
            HeaderG()

            Spacer(modifier = Modifier.height(32.dp))

            // Screen title
            Text(
                text = "Employee Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = LoginNavy
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "Sign in to access your workspace",
                fontSize = 15.sp,
                color = LoginGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // White card with both fields + button
            LoginFormCardG(navController = navController,
                employeeId = employeeId,
                password = password,
                loading = loading,
                error = error,
                onEmployeeIdChange = viewModel::updateEmployeeId,
                onPasswordChange = viewModel::updatePassword,
                onLoginClick = viewModel::login
            )
        }
    }
}

// ---------- White card: ID, password, forgot link, sign in button, footer note ----------
@Composable
fun LoginFormCardG( navController: NavController,
                    employeeId: String,
                    password: String,
                    loading: Boolean,
                    error: String,
                    onEmployeeIdChange: (String) -> Unit,
                    onPasswordChange: (String) -> Unit,
                    onLoginClick: () -> Unit) {
    // Simple local state, no ViewModel


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color(0x1A2F80D8),
                spotColor = Color(0x1A2F80D8)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {
        // Employee ID field
        InputFieldG(
            label = "Employee ID",
            placeholder = "Enter your employee ID",
            leadingIcon = Icons.Outlined.Person,
            value = employeeId,
            onValueChange = onEmployeeIdChange
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Password field (has the eye toggle)
        InputFieldG(
            label = "Password",
            placeholder = "Enter your password",
            leadingIcon = Icons.Outlined.Lock,
            value = password,
            onValueChange = onPasswordChange,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Forgot password - right aligned
        Text(
            text = "Forgot password?",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = LoginBlue,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { navController.navigate("forgot_password") }
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Gradient Sign In button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.verticalGradient(listOf(ButtonTop, ButtonBottom)))
                .clickable(enabled = !loading) {
                    onLoginClick()
                },//to be changed
            contentAlignment = Alignment.Center
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Sign In",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (error.isNotBlank()) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Small note under the button
        Text(
            text = "Authorized employees only",
            fontSize = 12.sp,
            color = LoginGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ---------- Reusable labelled input field (used for ID and Password) ----------
@Composable
fun InputFieldG(
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    // Eye toggle state lives here, only relevant for the password field
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        // Field label
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = LoginNavy
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, color = LoginNavy),
            placeholder = {
                Text(text = placeholder, fontSize = 14.sp, color = PlaceholderGray)
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = PlaceholderGray
                )
            },
            // Eye icon only for the password field
            trailingIcon = if (isPassword) {
                {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Outlined.VisibilityOff
                        else Icons.Outlined.Visibility,
                        contentDescription = "Toggle password visibility",
                        tint = PlaceholderGray,
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                }
            } else null,
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = LoginBlue,
                unfocusedBorderColor = FieldBorder,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

// ---------- Preview ----------
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployeeLoginScreenPreview() {
    EmployeeLoginScreenG(navController = rememberNavController())
}