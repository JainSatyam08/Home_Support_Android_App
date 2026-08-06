package com.example.homesupport.components.myrequestdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.homesupport.ui.theme.DarkGreen
import com.example.homesupport.ui.theme.DividerGray
import com.example.homesupport.ui.theme.LightGreenChip
import com.example.homesupport.ui.theme.NavyText
import com.example.homesupport.ui.theme.SubtleGray

@Composable
fun ScheduleCard(
    date: String,
    //day: String,
    timeSlot: String,
    modifier: Modifier = Modifier
) {
    SectionCard(modifier = modifier) {
        SectionHeader(icon = Icons.Outlined.CalendarMonth, title = "Schedule")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Preferred Date", fontSize = 11.sp, color = SubtleGray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = null,
                tint = NavyText,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = date, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NavyText)
        }
        //Text(text = day, fontSize = 12.sp, color = SubtleGray, modifier = Modifier.padding(start = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = DividerGray)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Preferred Time Slot", fontSize = 11.sp, color = SubtleGray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = NavyText,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = timeSlot, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NavyText)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = LightGreenChip
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.WatchLater,
                    contentDescription = null,
                    tint = DarkGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "We will arrive within your selected time slot",
                    fontSize = 11.sp,
                    color = DarkGreen
                )
            }
        }
    }
}