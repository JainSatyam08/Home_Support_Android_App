package com.example.homesupport.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// 1. Data Class: Backend se jo data aayega uske liye model
data class ServiceRequest(
    val id: String,
    val serviceName: String,
    val date: String,
    val status: String, // e.g., "Pending", "Completed", "Cancelled"
    val price: String
)

@Composable
fun Myrequests(nav: NavHostController) {
    // 2. Sample Data: Filhal dummy data hai, baad mein yahan Backend ki List aayegi
    val requestList = listOf(
        ServiceRequest("1", "Plumbing Repair", "24 Oct 2023", "Pending", "₹450"),
        ServiceRequest("2", "AC Cleaning", "20 Oct 2023", "Completed", "₹1200"),
        ServiceRequest("3", "Electrical Wiring", "15 Oct 2023", "Cancelled", "₹800"),
        ServiceRequest("4", "Home Painting", "10 Oct 2023", "Completed", "₹5000")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Service Requests",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 3. LazyColumn: Jo dynamic data ko handle karega
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(requestList) { request ->
                RequestItem(request)
            }
        }
    }
}

@Composable
fun RequestItem(request: ServiceRequest) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = request.serviceName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Date: ${request.date}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = request.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4CAF50) // Green color for price
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Status Badge
                Surface(
                    color = when (request.status) {
                        "Completed" -> Color(0xFFE8F5E9)
                        "Pending" -> Color(0xFFFFF3E0)
                        else -> Color(0xFFFFEBEE)
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = request.status,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = when (request.status) {
                            "Completed" -> Color(0xFF2E7D32)
                            "Pending" -> Color(0xFFEF6C00)
                            else -> Color(0xFFC62828)
                        }
                    )
                }
            }
        }
    }
}