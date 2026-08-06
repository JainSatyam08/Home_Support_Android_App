package com.example.homesupport.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import com.example.homesupport.components.myrequestdetail.CustomerDetailsSection
import com.example.homesupport.components.myrequestdetail.LocationSection
import com.example.homesupport.components.myrequestdetail.ProblemDescriptionSection
import com.example.homesupport.components.myrequestdetail.RequestDetailsBottomBar
import com.example.homesupport.components.myrequestdetail.RequestDetailsTopBar
import com.example.homesupport.components.myrequestdetail.RequestIdHeaderCard
import com.example.homesupport.components.myrequestdetail.RequestStatusSection
import com.example.homesupport.components.myrequestdetail.ScheduleCard
import com.example.homesupport.components.myrequestdetail.ServiceInformationCard
import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.ui.theme.BackgroundCream
import com.example.homesupport.viewmodel.AllRequestViewModel
import com.example.homesupport.viewmodel.RequestDetailViewModel


data class StatusStep(
    val label: String,
    val subLabel: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val isCompleted: Boolean,
    val isActive: Boolean
)

//data class RequestDetailsUiState(
//    val requestId: String = "HSPLB202510001",
//    val placedDate: String = "03 Aug 2025, 10:30 AM",
//    val status: String = "Confirmed",
//    val statusSteps: List<StatusStep> = listOf(
//        StatusStep("Request Placed", "03 Aug, 10:30 AM", Icons.Filled.Check, true, false),
//        StatusStep("Confirmed", "03 Aug, 11:00 AM", Icons.Filled.EditCalendar, true, true),
//        StatusStep("Partner Assigned", "Pending", Icons.Filled.Person, false, false),
//        StatusStep("Completed", "Pending", Icons.Filled.Flag, false, false)
//    ),
//    val serviceTitle: String = "Plumbing Service",
//    val serviceTag: String = "Leakage Repair",
//    val serviceDescription: String = "Bathroom pipe leakage and fittings replacement",
//    val serviceCategory: String = "Home Maintenance",
//    val preferredDate: String = "05 Aug 2025",
//    val preferredDay: String = "(Tuesday)",
//    val preferredTimeSlot: String = "10:00 AM \u2013 12:00 PM",
//    val address: String = "123, Green Park Apartment,\nSector 45, Noida,\nUttar Pradesh 201303",
//    val customerName: String = "Satyam Jain",
//    val customerPhone: String = "+91 98765 43210",
//    val customerEmail: String = "satyamjain20@gmail.com",
//    val problemDescription: String = "Water leaking from the washbasin pipe. Need immediate repair.",
//    val extraPhotoCount: Int = 2,
//    val totalAmount: String = "\u20B9599",
//    val isPaid: Boolean = true
//)


private fun getStatusSteps(status: String): List<StatusStep> {

    val currentStatus = status.uppercase()

    return listOf(
        StatusStep(
            label = "Request Placed",
            subLabel = "",
            icon = Icons.Filled.Check,
            isCompleted = true,
            isActive = false
        ),
        StatusStep(
            label = "Confirmed",
            subLabel = "",
            icon = Icons.Filled.EditCalendar,
            isCompleted = currentStatus in listOf(
                "CONFIRMED",
                "PARTNER ASSIGNED",
                "COMPLETED"
            ),
            isActive = currentStatus == "CONFIRMED"
        ),
        StatusStep(
            label = "Partner Assigned",
            subLabel = "",
            icon = Icons.Filled.Person,
            isCompleted = currentStatus == "COMPLETED",
            isActive = currentStatus == "PARTNER ASSIGNED"
        ),
        StatusStep(
            label = "Completed",
            subLabel = "",
            icon = Icons.Filled.Flag,
            isCompleted = currentStatus == "COMPLETED",
            isActive = currentStatus == "COMPLETED"
        )
    )
}
@Composable
fun MyRequestDetailScreen(navController: NavHostController,
                          bookingId: String) {
   // val uiState = RequestDetailsUiState() // in a real app this would come from a ViewModel
    val viewModel:  RequestDetailViewModel=hiltViewModel()
    LaunchedEffect(bookingId) {
        viewModel.getRequestDetail(bookingId)
    }

    val detail = viewModel.requestDetail
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return
        }

        error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(error)
            }
            return
        }

        detail == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No request details found")
            }
            return
        }
    }
    val request=detail

    val steps = getStatusSteps(request.Status)




    Scaffold(
        containerColor = BackgroundCream,
        topBar = {
            RequestDetailsTopBar(
                title = "Request Details",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            RequestDetailsBottomBar(
                //totalAmount = uiState.totalAmount,
                //isPaid = uiState.isPaid,
                onTrackRequestClick = { /* navController.navigate("track_request") */ }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                RequestIdHeaderCard(
                    requestId = request.bookingId,
                    placedDate = request.bookingDate,
                    status = request.Status
                )
            }
            item {
                RequestStatusSection(steps = steps)
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ServiceInformationCard(
                        title = request.serviceType,
                        tag = request.problemType,
                        description = request.problemDesc,
                        category = request.serviceType,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                    ScheduleCard(
                        date = request.prefDate,
                        timeSlot = request.prefSlot,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                }
            }
            item {
                LocationSection(address = request.Address)
            }
            item {
                CustomerDetailsSection(
                    name = request.CustomerForName,
                    phone = request.CustomerForCno,
                    email = request.CustomerEmail,
                    onContactClick = { /* launch dialer / call intent */ }
                )
            }
            item {
                ProblemDescriptionSection(
                    description = request.problemDesc,
                    extraPhotoCount = 2
                )
            }
            item { Spacer(modifier = Modifier.height(4.dp)) }
        }
    }
}



