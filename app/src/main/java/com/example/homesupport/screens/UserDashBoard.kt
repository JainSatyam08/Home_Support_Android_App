package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.Components.UserDashBoard.BottomBar
import com.example.homesupport.Components.UserDashBoard.DashboardHeader
import com.example.homesupport.Components.UserDashBoard.LocationBar
import com.example.homesupport.Components.UserDashBoard.ServiceGrid
import com.example.homesupport.Components.UserDashBoard.ServiceSearchBar
import com.example.homesupport.Components.UserDashBoard.SpecialFeatureCard
import com.example.homesupport.location.getAddressFromLocation
import com.example.homesupport.location.getCurrentLocation
import com.example.homesupport.permission.LocationPermissionHandler

@Composable
fun UserDashboard(nav: NavHostController) {
    DashboardContent(nav = nav, modifier = Modifier)
}

@Composable
fun DashboardContent(
    nav: NavHostController,
    modifier: Modifier
) {
    var permissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var address by remember {
        mutableStateOf("Fetching location...")
    }

    Box(modifier = modifier.fillMaxSize()) {
        // 1. Dashboard Header at the top layer background
        DashboardHeader()

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
                    .padding(top = 25.dp) // Increased top padding to move search bar down
            ) {
                item {
                    ServiceSearchBar()
                    Spacer(modifier = Modifier.height(12.dp))

                }


                item{ServiceGrid()
                    //Spacer(modifier = Modifier.height(12.dp))
                }

                item{SpecialFeatureCard(
                                title = "Quick Electrician",
                               description = "Get electrician within 30 minutes",
                                icon = Icons.Default.Build,
                                onClick = {
                                    // Navigate or open screen
                                }
                )}
            }

            BottomBar(navController = nav)

        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF0A1D56),
        modifier = Modifier.height(80.dp)
    ) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.History,
            BottomNavItem.Feedback,
            BottomNavItem.Category
        )
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedRoute == item.route,
                onClick = {
                    onItemSelected(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}
