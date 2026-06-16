package com.example.homesupport.components.newrequest.schedulerequest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.components.newrequest.NewRequestColors
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TealAccent
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TealDark

@Composable
fun ConfirmBookingButton(navController: NavHostController,) {
    Button(
        onClick = {
            navController.navigate(
                "confirmbooking"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = NewRequestColors.GreenButton
        )
    ) {
        Text(
            text = "Confirm Booking",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}
