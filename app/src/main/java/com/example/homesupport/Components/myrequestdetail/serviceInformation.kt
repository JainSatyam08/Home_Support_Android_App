package com.example.homesupport.components.myrequestdetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Plumbing
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.ui.theme.DarkGreen
import com.example.homesupport.ui.theme.DividerGray
import com.example.homesupport.ui.theme.NavyText
import com.example.homesupport.ui.theme.SubtleGray


@Composable
fun ServiceInformationCard(
    title: String,
    tag: String,
    description: String,
    category: String,
    modifier: Modifier = Modifier
) {
    SectionCard(modifier = modifier) {
        SectionHeader(icon = Icons.Outlined.Build, title = "Service Information")
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .border(1.5.dp, DarkGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Plumbing,
                contentDescription = null,
                tint = DarkGreen,
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyText)
        Spacer(modifier = Modifier.height(6.dp))
        Chip(text = tag)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, fontSize = 12.sp, color = SubtleGray)
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = DividerGray)
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = null,
                tint = SubtleGray,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(text = "Service Category", fontSize = 11.sp, color = SubtleGray)
                Text(text = category, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NavyText)
            }
        }
    }
}