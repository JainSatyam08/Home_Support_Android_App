package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.components.UserDashBoard.BottomBar
import com.example.homesupport.components.UserDashBoard.LocationBar   // ← your existing one
import com.example.homesupport.components.newrequest.*
import com.example.homesupport.location.getAddressFromLocation
import com.example.homesupport.location.getCurrentLocation
import com.example.homesupport.permission.LocationPermissionHandler



@Composable
fun NewRequestScreen(nav: NavHostController, serviceType: String?) {
    RequestContent(nav = nav, modifier = Modifier,serviceType)
}
@Composable
fun RequestContent(nav: NavHostController,
                     modifier: Modifier,
                   serviceType: String?) {

    var permissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var address by remember { mutableStateOf("Fetching location...") }


    Box(modifier = modifier.fillMaxSize()) {
        // 1. Dashboard Header at the top layer background
        Header(serviceType=serviceType)

        // 2. Main content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp) // Adjust based on header height
        ) {
            // Location Permission logic (Invisible)
            LocationPermissionHandler {
                permissionGranted = true
                getCurrentLocation(context) { location ->
                    location?.let {
                        address = getAddressFromLocation(
                            context,
                            it.latitude,
                            it.longitude
                        )
                    }
                }
            }

            // Location Bar - Spans full width
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (permissionGranted) {
                    LocationBar(address)
                } else {
                    Text(
                        text = "Location permission required",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp)) // Added space after LocationBar

            // The rest of the content with white background starting here
            LazyColumn(
                modifier = Modifier
                    //.fillMaxSize()
                    .weight(1f)
                    .offset(y = (-29).dp) // Reduced negative offset to move section down
                    .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(top = 25.dp, start = 16.dp, end = 16.dp) // Increased top padding to move search bar down
            ) {
                item {
                    Text(
                        text = "Service Details",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF1A1A1A)

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                //Spacer(50.dp)

                // 3b. Problem Description field
                item {
                    InputBox(placeholder = "Problem Description")
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // 3c. Photos / Video row
                item {
                    PhotosVideoRow(onAddPhoto = { /* TODO: launch picker */ })
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // 3d. Appliance category grid
                item {
                    ApplianceGrid(serviceType=serviceType)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // 3e. Proceed button
                item {
                    ProceedButton(onClick = { /* TODO: navigate */ })
                }
            }

            BottomBar(navController = nav)

        }
    }
}

