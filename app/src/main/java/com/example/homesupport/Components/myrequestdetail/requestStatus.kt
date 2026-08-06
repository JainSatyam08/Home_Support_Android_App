package com.example.homesupport.components.myrequestdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.homesupport.screens.StatusStep
import com.example.homesupport.ui.theme.DarkGreen
import com.example.homesupport.ui.theme.NavyText
import com.example.homesupport.ui.theme.PendingGray
import com.example.homesupport.ui.theme.PrimaryGreen
import com.example.homesupport.ui.theme.SubtleGray

@Composable
fun RequestStatusSection(steps: List<StatusStep>) {
    SectionCard {
        SectionHeader(icon = Icons.Outlined.WatchLater, title = "Request Status")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            steps.forEachIndexed { index, step ->
                StatusStepItem(step = step, modifier = Modifier.weight(1f))
                if (index != steps.lastIndex) {
                    Box(
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(top = 20.dp)
                            .height(2.dp)
                            .background(if (step.isCompleted) PrimaryGreen else PendingGray)
                    )
                }
            }
        }
    }
}

@Composable
fun StatusStepItem(step: StatusStep, modifier: Modifier = Modifier) {
    val circleColor = when {
        step.isActive -> DarkGreen
        step.isCompleted -> PrimaryGreen
        else -> PendingGray
    }
    val iconTint = if (step.isCompleted || step.isActive) Color.White else Color(0xFF9E9E9E)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = step.icon,
                contentDescription = step.label,
                tint = iconTint,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = step.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = NavyText,
            textAlign = TextAlign.Center
        )
        Text(
            text = step.subLabel,
            fontSize = 11.sp,
            color = SubtleGray,
            textAlign = TextAlign.Center
        )
    }
}
