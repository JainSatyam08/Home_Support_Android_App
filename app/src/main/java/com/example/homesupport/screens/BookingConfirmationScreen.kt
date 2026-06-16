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
object BookingTheme {
    val TealPrimary    = Color(0xFF2A7D6F)
    val TealDark       = Color(0xFF1E5F55)
    val BackgroundGray = Color(0xFFF2F6F8)
    val CardWhite      = Color(0xFFFFFFFF)
    val TextPrimary    = Color(0xFF1A1A1A)
    val TextSecondary  = Color(0xFF6B7280)
    val DividerColor   = Color(0xFFE5E7EB)
}

// ─────────────────────────────────────────────
// DATA MODEL
// ─────────────────────────────────────────────


// ─────────────────────────────────────────────
// ROOT SCREEN
// ─────────────────────────────────────────────
@Composable
fun BookingConfirmedScreen(
    navController: NavHostController,
    bookingViewModel: BookingViewModel
) {
    Log.d("BOOKING", bookingViewModel.serviceType)
    Log.d("BOOKING", bookingViewModel.problemType)
    Log.d("BOOKING", bookingViewModel.problemDescription)

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
            BookingDetailCard(bookingViewModel = bookingViewModel)
            BookingActionButtons(
                onTrackRequest = { navController.navigate(Screen.Requests.route) },
                onBackToHome   = { navController.navigate(Screen.Home.route) }
            )
        }
    }
}
