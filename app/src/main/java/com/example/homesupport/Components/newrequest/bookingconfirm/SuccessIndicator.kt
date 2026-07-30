package com.example.homesupport.components.newrequest.bookingconfirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.ui.theme.TealPrimary
import com.example.homesupport.ui.theme.TextSecondary


@Composable
fun SuccessIndicatorSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(TealPrimary),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Booking confirmed",
            tint = Color.White,
            modifier = Modifier.size(52.dp)
        )
    }
    // Swap Box above for a LottieAnimation composable when you have
    // an animated checkmark asset — same slot, zero refactor needed.
}


@Composable
fun ConfirmationMessageText(
    message: String = "Your booking has been successfully confirmed. A Service Partner is preparing for your visit.",
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        fontSize = 16.sp,
        color =TextSecondary,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
        modifier = modifier.fillMaxWidth()
    )
}
