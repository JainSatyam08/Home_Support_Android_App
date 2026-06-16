package com.example.homesupport.components.newrequest.bookingconfirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlot
import com.example.homesupport.screens.BookingTheme
import com.example.homesupport.viewmodel.BookingViewModel
import java.time.LocalDate

@Composable
fun BookingDetailCard(
    bookingViewModel: BookingViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BookingTheme.CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ServiceHeaderRow(
                serviceTitle = bookingViewModel.serviceType
            )

            HorizontalDivider(color = BookingTheme.DividerColor, thickness = 1.dp)

            ProblemTypeRow(
                problemType = bookingViewModel.problemType
            )

            HorizontalDivider(
                color = BookingTheme.DividerColor,
                thickness = 1.dp
            )

            ProblemDescriptionRow(
                description = bookingViewModel.problemDescription
            )

            HorizontalDivider(
                color = BookingTheme.DividerColor,
                thickness = 1.dp
            )

            AppointmentTimeRow(
                date = bookingViewModel.selectDate,
                slot = bookingViewModel.selectSlot
            )
        }
    }
}

@Composable
private fun ServiceHeaderRow(
    serviceTitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column {
            Text(
                text = "Active Request:",
                fontSize = 12.sp,
                color = BookingTheme.TextSecondary
            )
            Text(
                text = serviceTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = BookingTheme.TextPrimary
            )
        }
    }
}

@Composable
fun ProblemTypeRow(
    problemType: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Problem Type",
            color = BookingTheme.TextSecondary
        )

        Text(
            text = problemType,
            fontWeight = FontWeight.SemiBold,
            color = BookingTheme.TextPrimary
        )
    }
}

@Composable
fun ProblemDescriptionRow(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Problem Description",
            fontSize = 14.sp,
            color = BookingTheme.TextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description.ifBlank { "No Description" },
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = BookingTheme.TextPrimary
        )
    }
}

@Composable
fun AppointmentTimeRow(
    date: LocalDate,
    slot: TimeSlot?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = slot?.label ?: "Not Selected",
            fontSize = 14.sp,
            color = BookingTheme.TextSecondary
        )
        Text(
            text = date.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = BookingTheme.TextPrimary
        )
    }
}
