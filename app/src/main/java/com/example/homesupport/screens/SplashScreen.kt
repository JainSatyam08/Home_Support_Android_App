package com.example.homesupport.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(nav: NavHostController){
    val scale= remember{
        Animatable(0f)
    }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )

        )

        delay(1000)

        nav.navigate("user_dashboard") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Main title animated
            Text(
                text = "Home Support",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4A90E2),
                modifier = Modifier.scale(scale.value) // animated effect
            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Your Daily Service Partner",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        }
    }
}