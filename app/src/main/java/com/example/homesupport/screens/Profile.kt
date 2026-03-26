package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.homesupport.components.profile.HeaderSection
import com.example.homesupport.components.profile.MainCardSection

@Composable
fun ProfileScreen(
    nav: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        HeaderSection()

        MainCardSection()
    }
}