package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.homesupport.components.myrequests.MyRequestsHeader
import com.example.homesupport.components.myrequests.RequestList
import com.example.homesupport.components.myrequests.RequestToggle

// YAHA FUTURE MAI VIEWMODEL SE STATE AAYEGA(LIVE DATA/STATEFLOW)
// SHIFT ISACTIVE TO VIEWMODEL
@Composable
fun MyRequests(nav: NavHostController) {
    var isActive by remember { mutableStateOf(true) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)) // light background
    ) {

        // 🔹 Header
        MyRequestsHeader()

        // 🔹 Toggle
        RequestToggle(
            isActive = isActive,
            onToggleChange = { isActive = it }
        )

        // 🔹 List Area (abhi empty, baad mein cards aayenge)
        RequestList(isActive=isActive)
    }
}