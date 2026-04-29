package com.example.homesupport.components.newrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.components.newrequest.NewRequestColors
// ─── Colors shared across newrequest components ───────────────────────────────


/**
 * Gradient header bar that shows the screen title.
 * Height is kept compact (80 dp) so the LocationBar sits snugly below it.
 */
@Composable
fun Header(modifier: Modifier = Modifier,serviceType:String?) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors=listOf(
                        Color(0xFF1E5F8C),
                        Color(0xFF2F80ED)
                    )
                )
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // ⚡ replace with your actual vector drawable via Icon() if you have one
            Text(text = "⚡", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.White, fontWeight = FontWeight.Normal)) {
                        append("Request: ")
                    }
                    withStyle(SpanStyle(color = Color(0xFFB2DFDB), fontWeight = FontWeight.Bold)) {
                        append(serviceType)
                    }
                },
                fontSize = 18.sp
            )
        }
    }
}