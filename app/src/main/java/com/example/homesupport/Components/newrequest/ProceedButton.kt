package com.example.homesupport.components.newrequest


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

/**
 * Full-width CTA button at the bottom of the request form.
 */


@Composable
fun ProceedButton(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            navController.navigate(
                "schedulescreen"
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = NewRequestColors.GreenButton
        )
    ) {
        Text(
            text = "Proceed to Schedule",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}