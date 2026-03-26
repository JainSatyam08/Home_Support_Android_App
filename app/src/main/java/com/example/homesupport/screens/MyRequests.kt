package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.homesupport.components.myrequests.MyRequestsHeader
import com.example.homesupport.components.myrequests.RequestList
import com.example.homesupport.components.myrequests.RequestToggle

@Composable
fun MyRequests(nav: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)) // light background
    ) {

        // 🔹 Header
        MyRequestsHeader()

        // 🔹 Toggle
        RequestToggle()

        // 🔹 List Area (abhi empty, baad mein cards aayenge)
        RequestList()
    }
}