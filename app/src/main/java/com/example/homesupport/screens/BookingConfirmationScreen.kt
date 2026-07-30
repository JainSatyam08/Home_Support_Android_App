package com.example.homesupport.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homesupport.components.UserDashBoard.BottomBar
import com.example.homesupport.components.UserDashBoard.Screen
import com.example.homesupport.components.newrequest.bookingconfirm.BookingActionButtons
import com.example.homesupport.components.newrequest.bookingconfirm.BookingDetailCard
import com.example.homesupport.components.newrequest.bookingconfirm.ConfirmationMessageText
import com.example.homesupport.components.newrequest.bookingconfirm.SuccessIndicatorSection
import com.example.homesupport.viewmodel.BookingViewModel

// ─────────────────────────────────────────────
// DESIGN TOKENS
// ─────────────────────────────────────────────


@Composable
fun BookingConfirmedScreen(
    navController: NavHostController,
    bookingViewModel: BookingViewModel
) {
    val response = bookingViewModel.bookingResponse

    if (response == null) {
        return
    }


    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SuccessIndicatorSection()
            ConfirmationMessageText()
            BookingDetailCard(
                bookingId = response.bookingId,
                trackId = response.trackId,
                customerName = response.customerName,
                phoneNumber = response.phoneNumber,
                serviceType = response.serviceType,
                status = response.status
            )

            BookingActionButtons(
                onTrackRequest = {
                    navController.navigate(Screen.Requests.route){
                    popUpTo("user_dashboard") {
                        inclusive = false
                    }
                    launchSingleTop = true
                } },
                onBackToHome   = {
                    bookingViewModel.clearBookingState()
                    navController.navigate(Screen.Home.route){
                    popUpTo("user_dashboard") {
                        inclusive = false
                    }
                    launchSingleTop = true
                } }
            )
        }
    }
}
