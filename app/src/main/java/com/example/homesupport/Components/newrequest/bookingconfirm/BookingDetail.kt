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
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.DividerColor
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextPrimary
import com.example.homesupport.components.newrequest.schedulerequest.ScheduleScreenColr.TextSecondary
import com.example.homesupport.components.newrequest.schedulerequest.TimeSlot
import com.example.homesupport.ui.theme.CardWhite

import com.example.homesupport.viewmodel.BookingViewModel
import java.time.LocalDate

@Composable
fun BookingDetailCard(
    bookingId: String,
    trackId: Long,
    customerName: String,
    phoneNumber: String,
    serviceType: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            BookingIdRow(
                bookingId=bookingId
            )
            HorizontalDivider(color = DividerColor, thickness = 1.dp)
            TrackIdRow(
                TrackId=trackId.toString()
            )
            HorizontalDivider(color = DividerColor, thickness = 1.dp)
            ServiceHeaderRow(
                serviceTitle = serviceType
            )

            HorizontalDivider(color = DividerColor, thickness = 1.dp)

           CustomerNameRow(
                customerName = customerName
            )

            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp
            )

           PhoneNumberRow(
                phoneNumber = phoneNumber
           )

            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp
            )

            StatusRow(
                status=status
           )
        }
    }
}

@Composable
private fun BookingIdRow(
    bookingId: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column {
            Text(
                text = "Booking ID:",
                fontSize = 12.sp,
                color = TextSecondary
            )
            Text(
                text = bookingId,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
    }

}
@Composable
private fun TrackIdRow(
    TrackId: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column {
            Text(
                text = "Track ID:",
                fontSize = 12.sp,
                color = TextSecondary
            )
            Text(
                text = TrackId,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
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
                color = TextSecondary
            )
            Text(
                text = serviceTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
    }
}

@Composable
fun CustomerNameRow(
    customerName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Customer Name",
            color = TextSecondary
        )

        Text(
            text = customerName,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )
    }
}

@Composable
fun PhoneNumberRow(
    phoneNumber: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Phone Number",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = phoneNumber,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )
    }
}

@Composable
fun StatusRow(
    status: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Status",
            fontSize = 14.sp,
            color = TextSecondary
        )
        Text(
            text = status,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}
