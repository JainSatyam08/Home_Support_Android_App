package com.example.homesupport.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.components.UserDashBoard.BottomBar
import com.example.homesupport.components.UserDashBoard.DashboardHeader

import com.example.homesupport.components.UserDashBoard.LocationBar
import com.example.homesupport.components.UserDashBoard.ServiceGrid
import com.example.homesupport.components.UserDashBoard.ServiceSearchBar
import com.example.homesupport.components.UserDashBoard.SpecialFeatureCard
import com.example.homesupport.components.myrequestdetail.RequestDetailsBottomBar
import com.example.homesupport.components.myrequestdetail.RequestDetailsTopBar
import com.example.homesupport.location.LocationData
import com.example.homesupport.location.LocationManager
import com.example.homesupport.location.getAddressFromLocation
import com.example.homesupport.location.getCurrentLocation
import com.example.homesupport.permission.LocationPermissionHandler
import com.example.homesupport.ui.theme.BackgroundCream
import com.example.homesupport.ui.theme.BlueDeep
import com.example.homesupport.ui.theme.BlueTop

@Composable
fun UserDashboard(nav: NavHostController,
                  locationData: LocationData) {
    DashboardContent(nav = nav, modifier = Modifier,locationData=locationData)
}

@Composable
fun DashboardContent(
    nav: NavHostController,
    modifier: Modifier,
    locationData: LocationData
) {




        Box(modifier = modifier.fillMaxSize()) {
            // 1. Dashboard Header at the top layer background
            DashboardHeader()



            // 2. Main content Column
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp) // Adjust based on header height
            ) {



                // Location Bar - Spans full width
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    LocationBar(locationData.address)
                }

                Spacer(modifier = Modifier.height(10.dp)) // Added space after LocationBar

                // The rest of the content with white background starting here
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .offset(y = (-29).dp) // Reduced negative offset to move section down
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                        .padding(top = 25.dp) // Increased top padding to move search bar down
                ) {
                    item {
                        ServiceSearchBar()
                        Spacer(modifier = Modifier.height(12.dp))

                    }


                    item {
                        ServiceGrid(navController = nav)
                        //Spacer(modifier = Modifier.height(12.dp))
                    }

                    item {
                        SpecialFeatureCard(
                            title = "Quick Electrician",
                            description = "Get electrician within 30 minutes",
                            icon = Icons.Default.Build,
                            onClick = {
                                // Navigate or open screen
                            }
                        )
                    }

                }

                    //Spacer(modifier = Modifier.height(12.dp))
                BottomBar(nav)

            }
        //}
    }
}


// ---------------------------------------------------------------------------
// COLORS
// ---------------------------------------------------------------------------




// ---------------------------------------------------------------------------
// MAIN SCREEN - only NavHostController is passed in
// ---------------------------------------------------------------------------


