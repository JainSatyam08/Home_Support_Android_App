package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.homesupport.components.myrequests.MyRequestsHeader
import com.example.homesupport.components.myrequests.RequestList
import com.example.homesupport.components.myrequests.RequestToggle
import com.example.homesupport.dto.AllServiceResponse
import com.example.homesupport.viewmodel.AllRequestViewModel

// YAHA FUTURE MAI VIEWMODEL SE STATE AAYEGA(LIVE DATA/STATEFLOW)
// SHIFT ISACTIVE TO VIEWMODEL
@Composable
fun MyRequests(nav: NavHostController) {
    var isActive by remember { mutableStateOf(true) }
    val viewModel: AllRequestViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getAllRequest()
    }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)) // light background
    ) {

        // 🔹 Header
        MyRequestsHeader()

        // 🔹 Toggle
        RequestToggle(
            isActive = isActive,
            onToggleChange = { isActive = it }
        )

        when {
            viewModel.isLoading -> {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CircularProgressIndicator()

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Loading your requests...",
                            color = Color.Gray
                        )
                    }
                }
            }

            viewModel.errorMessage != null -> {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(60.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Oops!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = viewModel.errorMessage ?: "Something went wrong",
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                viewModel.getAllRequest()
                            }
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }

            viewModel.allRequests.isEmpty() -> {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Default.Assignment,
                            contentDescription = null,
                            modifier = Modifier.size(70.dp),
                            tint = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "No Requests Yet",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Book your first service to see it here.",
                            color = Color.Gray
                        )
                    }
                }
            }

            else -> {

                RequestList(
                    isActive = isActive,
                    requestList = viewModel.allRequests,
                    nav
                )
            }
        }
    }
}