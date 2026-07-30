package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.homesupport.components.profile.HeaderSection
import com.example.homesupport.components.profile.MainCardSection
import com.example.homesupport.viewmodel.LogoutViewModel

@Composable
fun ProfileScreen(
    nav: NavHostController
) {
    val logoutViewModel: LogoutViewModel = hiltViewModel()
    LaunchedEffect(logoutViewModel.logoutSuccess) {

        if (logoutViewModel.logoutSuccess) {

            nav.navigate("login") {

                popUpTo(0) {
                    inclusive = true
                }

                launchSingleTop = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        HeaderSection()

        MainCardSection()
    }
}