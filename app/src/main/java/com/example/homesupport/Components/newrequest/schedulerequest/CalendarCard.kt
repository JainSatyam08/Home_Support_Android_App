package com.example.homesupport.components.newrequest.schedulerequest

import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.YearMonth

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight

import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.CardBg
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.SelectedGreen
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextPrimary
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextSecondary

import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarCard(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Month navigation
            MonthNavigationRow(
                currentMonth = currentMonth,
                onPreviousMonth = onPreviousMonth,
                onNextMonth = onNextMonth
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Day-of-week labels
            WeekDayHeader()

            Spacer(modifier = Modifier.height(8.dp))

            // Date grid
            CalendarGrid(
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                onDateSelected = onDateSelected
            )
        }
    }
}

@Composable
fun MonthNavigationRow(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Previous Month",
                tint = TextPrimary
            )
        }

        val monthName = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        Text(
            text = "$monthName ${currentMonth.year}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = TextPrimary
        )

        IconButton(onClick = onNextMonth) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Next Month",
                tint = TextPrimary
            )
        }
    }
}

@Composable
fun WeekDayHeader() {
    val days = listOf("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa")
    Row(modifier = Modifier.fillMaxWidth()) {
        days.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                color = TextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CalendarGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDay = currentMonth.atDay(1)
    // dayOfWeek: MONDAY=1 … SUNDAY=7; we want Sunday=0
    val startOffset = (firstDay.dayOfWeek.value % 7)
    val daysInMonth = currentMonth.lengthOfMonth()

    // Build a 6-row × 7-col grid
    val totalCells = startOffset + daysInMonth
    val rows = (totalCells + 6) / 7

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(rows) { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(7) { col ->
                    val cellIndex = row * 7 + col
                    val dayNumber = cellIndex - startOffset + 1

                    if (dayNumber in 1..daysInMonth) {
                        val date = currentMonth.atDay(dayNumber)
                        CalendarDayCell(
                            day = dayNumber,
                            isSelected = date == selectedDate,
                            modifier = Modifier.weight(1f),
                            onClick = { onDateSelected(date) }
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarDayCell(
    day: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(CircleShape)
            .then(
                if (isSelected)
                    Modifier.background(SelectedGreen)
                else
                    Modifier
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            color = if (isSelected) Color.White else TextPrimary,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}