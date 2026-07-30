package com.example.homesupport.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homesupport.components.newrequest.schedulerequest.CalendarCard
import com.example.homesupport.components.newrequest.schedulerequest.ConfirmBookingButton
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleHeader
import com.example.homesupport.components.newrequest.schedulerequest.ServiceSummaryRow
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlotSection
import com.example.homesupport.viewmodel.BookingViewModel
import java.time.YearMonth


@Composable
fun ScheduleServiceScreen(nav: NavHostController, bookingViewModel: BookingViewModel) {

    val error = bookingViewModel.errorMessage
    val context = LocalContext.current
    LaunchedEffect(error) {

        if(error != null){

            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()

            bookingViewModel.clearError()
        }
    }

    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val response = bookingViewModel.bookingResponse

    LaunchedEffect(response) {
        if (response != null) {
            nav.navigate("confirmbooking")
        }
    }

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
            selectedDate = bookingViewModel.selectDate,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) },
            onDateSelected = { date ->
                bookingViewModel.updateSelectDate(date)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Time Slot Selector
        TimeSlotSection(
            selectedSlot = bookingViewModel.selectSlot,
            onSlotSelected = { slot ->
                bookingViewModel.updateSelectSlot(slot)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 4. Service Summary Row
        ServiceSummaryRow(
            serviceName = "Appliance Repair",
            serviceDetail = "1 IMAGE 0"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 5. Confirm Button
        ConfirmBookingButton(enabled = !bookingViewModel.isLoading,
            onClick = {bookingViewModel.bookingRequest()})

        Spacer(modifier = Modifier.height(24.dp))
    }
}
