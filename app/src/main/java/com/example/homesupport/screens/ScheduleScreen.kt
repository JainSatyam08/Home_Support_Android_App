package com.example.homesupport.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homesupport.components.newrequest.schedulerequest.CalendarCard
import com.example.homesupport.components.newrequest.schedulerequest.ConfirmBookingButton
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleHeader
import com.example.homesupport.components.newrequest.schedulerequest.ServiceSummaryRow
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlot
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlotSection
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun ScheduleServiceScreen(nav: NavHostController){
    var selectedDate by remember { mutableStateOf(LocalDate.of(2026, 3, 9)) }
    var currentMonth by remember { mutableStateOf(YearMonth.of(2026, 3)) }
    var selectedSlot by remember { mutableStateOf<TimeSlot?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Top Header
        ScheduleHeader(
            address = "2235 Slassmoin Road, Pakara, Suite V2 …"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Calendar Card
        CalendarCard(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth    = { currentMonth = currentMonth.plusMonths(1) },
            onDateSelected = { selectedDate = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Time Slot Selector
        TimeSlotSection(
            selectedSlot = selectedSlot,
            onSlotSelected = { selectedSlot = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 4. Service Summary Row
        ServiceSummaryRow(
            serviceName = "Appliance Repair",
            serviceDetail = "1 IMAGE 0"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 5. Confirm Button
        ConfirmBookingButton(onClick = {

        })

        Spacer(modifier = Modifier.height(24.dp))
    }
}
