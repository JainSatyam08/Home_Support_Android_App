package com.example.homesupport.components.newrequest.schedulerequest


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.CardBg
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.DividerColor
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.SelectedGreen
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TealLight
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextPrimary
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextSecondary

enum class TimeSlot(
    val label: String,
    val timeRange: String
) {
    MORNING("Morning", "9 AM - 12 PM"),
    AFTERNOON("Afternoon", "12 PM - 4 PM"),
    EVENING("Evening", "4 PM - 8 PM"),
    ASAP("ASAP", "")
}

@Composable
fun TimeSlotSection(
    selectedSlot: TimeSlot?,
    onSlotSelected: (TimeSlot) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Select Time Slot",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TimeSlot.entries.forEach { slot ->
                TimeSlotCard(
                    slot = slot,
                    isSelected = slot == selectedSlot,
                    modifier = Modifier.weight(1f),
                    onClick = { onSlotSelected(slot) }
                )
            }
        }
    }
}

@Composable
fun TimeSlotCard(
    slot: TimeSlot,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) SelectedGreen else DividerColor
    val bgColor     = if (isSelected) TealLight else CardBg

    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon for each slot
            TimeSlotIcon(slot = slot, isSelected = isSelected)

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = slot.label,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) SelectedGreen else TextPrimary,
                textAlign = TextAlign.Center
            )

            if (slot.timeRange.isNotEmpty()) {
                Text(
                    text = slot.timeRange,
                    fontSize = 9.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 12.sp
                )
            }
        }
    }
}

@Composable
fun TimeSlotIcon(slot: TimeSlot, isSelected: Boolean) {
    val tint = if (isSelected) SelectedGreen else TextSecondary
    val icon: ImageVector = when (slot) {
        TimeSlot.MORNING, TimeSlot.AFTERNOON, TimeSlot.EVENING ->
            Icons.Default.ElectricBolt          // replace with sun/moon icons from your assets
        TimeSlot.ASAP ->
            Icons.Default.ElectricBolt
    }
    Icon(
        imageVector = icon,
        contentDescription = slot.label,
        tint = tint,
        modifier = Modifier.size(24.dp)
    )
}