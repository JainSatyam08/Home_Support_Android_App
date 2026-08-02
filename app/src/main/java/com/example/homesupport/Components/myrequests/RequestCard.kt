package com.example.homesupport.components.myrequests

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.dto.AllServiceResponse

data class RequestData(
    val title: String,
    val isTrusted: Boolean = false,
    val workerName: String? = null,
    val statusText: String? = null,
    val serviceId: String? = null,
    val serviceName: String? = null,
    val showBookAgain: Boolean = false
)
@Composable
fun RequestCard(data: AllServiceResponse) {

    Card(
        modifier = Modifier.fillMaxWidth()
                            .clickable {},
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(12.dp)
        ) {

            RequestCardHeader(
                serviceType = data.serviceType,
                status=data.status
            )

            Spacer(modifier = Modifier.height(10.dp))

            RequestDetails(data)


            Spacer(modifier = Modifier.height(12.dp))

            ActionButtons()
        }
    }
}

@Composable
fun RequestCardHeader(serviceType:String, status:String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = serviceType,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .background(
                    color = when (status) {
                        "Completed" -> Color(0xFFE8F5E9)
                        "Cancelled" -> Color(0xFFFFEBEE)
                        "Pending" -> Color(0xFFFFF3E0)
                        else -> Color(0xFFE3F2FD)
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(
                text = status,
                color = when (status) {
                    "Completed" -> Color(0xFF2E7D32)
                    "Cancelled" -> Color.Red
                    "Pending" -> Color(0xFFFF9800)
                    else -> Color(0xFF1565C0)
                },
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
        }
    }
}
@Composable
fun RequestDetails(data: AllServiceResponse) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Booking ID : ",
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = data.bookingId
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Track ID : ",
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = data.trackId.toString()
            )
        }


        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Updated : ",
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = data.statusUpdatedAt
            )
        }

    }
}


@Composable
fun ActionButtons() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color(0xFF2E7D32)),
            modifier = Modifier.weight(1f)
        ) {
            Text("View Details")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color(0xFF1565C0)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Contact")
        }


    }
}