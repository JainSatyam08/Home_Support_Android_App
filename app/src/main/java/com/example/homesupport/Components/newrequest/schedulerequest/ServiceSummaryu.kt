package com.example.homesupport.components.newrequest.schedulerequest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.CardBg
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.DividerColor
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.SelectedGreen
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TealLight
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextPrimary
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextSecondary

@Composable
fun ServiceSummaryRow(
    serviceName: String,
    serviceDetail: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CardBg)
            .border(1.dp, DividerColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Service icon placeholder
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(TealLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ElectricBolt, // replace with appliance icon
                contentDescription = null,
                tint = SelectedGreen,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = serviceName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = TextPrimary
            )
            Text(
                text = serviceDetail,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Details",
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )
    }
}