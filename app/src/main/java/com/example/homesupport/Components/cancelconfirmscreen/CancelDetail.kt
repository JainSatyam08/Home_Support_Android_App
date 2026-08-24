package com.example.homesupport.components.cancelconfirmscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.homesupport.screens.CancellationDetail
import com.example.homesupport.ui.theme.BrandGreen
import com.example.homesupport.ui.theme.SoftGreenBg
import com.example.homesupport.ui.theme.TextDark

@Composable
fun CancellationDetailsCard(details: List<CancellationDetail>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Cancellation Details",
                color = TextDark,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            details.forEachIndexed { index, detail ->
                DetailRow(detail = detail)
                if (index != details.lastIndex) {
                    HorizontalDivider(color = Color(0xFFEDEFF2), thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun DetailRow(detail: CancellationDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(SoftGreenBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = detail.icon,
                contentDescription = null,
                tint = BrandGreen,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = detail.label,
            color = TextDark,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = detail.value,
            color = if (detail.label == "Request ID") BrandGreen else TextDark,
            fontSize = 14.sp,
            fontWeight = if (detail.label == "Request ID") FontWeight.Bold else FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1.2f)
        )
    }
}
