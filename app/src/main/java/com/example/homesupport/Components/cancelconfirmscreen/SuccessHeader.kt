package com.example.homesupport.components.cancelconfirmscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.ui.theme.BrandGreen
import com.example.homesupport.ui.theme.DotBlue
import com.example.homesupport.ui.theme.DotGreen
import com.example.homesupport.ui.theme.SoftGreenBg
import com.example.homesupport.ui.theme.SoftGreenCircle
import com.example.homesupport.ui.theme.TextDark
import com.example.homesupport.ui.theme.TextMuted

@Composable
fun SuccessHeader(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SoftGreenBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SuccessCheckBadge()

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = title,
                color = TextDark,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = subtitle,
                color = TextMuted,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
    }
}

/**
 * The big green checkmark circle with small scattered dots
 * around it, matching the celebratory illustration.
 */
@Composable
fun SuccessCheckBadge() {
    Box(
        modifier = Modifier.size(140.dp),
        contentAlignment = Alignment.Center
    ) {
        // Scattered decorative dots
        DecorativeDot(color = DotGreen, size = 8.dp, alignment = Alignment.TopStart, offsetX = 4.dp, offsetY = 26.dp)
        DecorativeDot(color = DotGreen, size = 6.dp, alignment = Alignment.TopEnd, offsetX = (-8).dp, offsetY = 14.dp)
        DecorativeDot(color = DotBlue, size = 6.dp, alignment = Alignment.BottomStart, offsetX = 20.dp, offsetY = (-18).dp)
        DecorativeDot(color = DotBlue, size = 7.dp, alignment = Alignment.CenterEnd, offsetX = (-2).dp, offsetY = 0.dp)
        DecorativeDot(color = DotGreen, size = 6.dp, alignment = Alignment.BottomEnd, offsetX = (-18).dp, offsetY = (-26).dp)

        // Soft outer circle
        Box(
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
                .background(SoftGreenCircle),
            contentAlignment = Alignment.Center
        ) {
            // Solid checkmark circle
            Box(
                modifier = Modifier
                    .size(78.dp)
                    .clip(CircleShape)
                    .background(BrandGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Success",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun BoxScope.DecorativeDot(
    color: Color,
    size: Dp,
    alignment: Alignment,
    offsetX: Dp,
    offsetY: Dp
) {
    Box(
        modifier = Modifier
            .align(alignment)
            .offset(x = offsetX, y = offsetY)
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}