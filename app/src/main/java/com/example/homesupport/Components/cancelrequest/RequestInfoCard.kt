package com.example.homesupport.components.cancelrequest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.example.homesupport.ui.theme.BrandBlueLight
import com.example.homesupport.ui.theme.BrandGreen
import com.example.homesupport.ui.theme.SoftGreenBg
import com.example.homesupport.ui.theme.TextDark
import com.example.homesupport.ui.theme.TextMuted

@Composable
fun RequestInfoCard(
    requestId: String,
    serviceName: String,
    serviceIcon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y= (-18).dp), // sits slightly over the top bar's rounded edge
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularIconBadge(
                icon = Icons.Filled.Description,
                backgroundColor = SoftGreenBg,
                tint = BrandGreen
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "Request ID")
                Text(
                    text = requestId,
                    color = BrandGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                LabelText(text = "Service")
                Text(
                    text = serviceName,
                    color = TextDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            CircularIconBadge(
                icon = serviceIcon,
                backgroundColor = Color(0xFFEAF1FC),
                tint = BrandBlueLight,
                size = 52.dp
            )
        }
    }
}
@Composable
fun LabelText(text: String) {
    Text(
        text = text,
        color = TextMuted,
        fontSize = 13.sp
    )
}