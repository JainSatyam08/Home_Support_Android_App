package com.example.homesupport.components.myrequests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RequestList() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(3) {

            val sample = listOf(
                RequestData(
                    title = "AC Repair",
                    isTrusted = true,
                    workerName = "Rahul K.",
                    statusText = "On the Way (15 mins)"
                ),
                RequestData(
                    title = "Home Cleaning",
                    serviceId = "236 Repair",
                    serviceName = "Home Cleaning",
                    showBookAgain = true
                ),
                RequestData(
                    title = "Electrical Wiring",
                    serviceId = "235 Repair",
                    serviceName = "Vector Contour",
                    showBookAgain = true
                )
            )

            RequestCard(sample[it])
        }
    }
}