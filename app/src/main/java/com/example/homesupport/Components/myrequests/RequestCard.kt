package com.example.homesupport.components.myrequests

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
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
fun RequestCard(data: RequestData) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(12.dp)
        ) {

            RequestCardHeader(
                title = data.title,
                isTrusted = data.isTrusted
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (data.workerName != null) {
                WorkerInfo(
                    name = data.workerName,
                    status = data.statusText ?: ""
                )
            } else {
                ServiceInfo(
                    id = data.serviceId ?: "",
                    name = data.serviceName ?: ""
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            ActionButtons(
                showBookAgain = data.showBookAgain
            )
        }
    }
}

@Composable
fun RequestCardHeader(title: String, isTrusted: Boolean) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        if (isTrusted) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF2E7D32), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "trusted green",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun WorkerInfo(name: String, status: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        // Dummy profile
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.LightGray, shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = name, fontWeight = FontWeight.SemiBold)
            Text(text = status, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun ServiceInfo(id: String, name: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = id, fontSize = 12.sp, color = Color.Gray)
            Text(text = name, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun ActionButtons(showBookAgain: Boolean) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color(0xFF2E7D32)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Track")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color(0xFF1565C0)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Contact")
        }

        if (showBookAgain) {
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text("Book Again")
            }
        }
    }
}