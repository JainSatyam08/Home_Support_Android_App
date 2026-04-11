package com.example.homesupport.screens


import android.preference.PreferenceActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.components.UserDashBoard.LocationBar
import com.example.homesupport.components.newrequest.HeaderSection
import com.example.homesupport.components.newrequest.LocationCard
import com.example.homesupport.components.newrequest.ServiceDetailField

data class ServiceOption(
    val title: String,
    val imageRes: Int // drawable resource
)

val plumberOptions = listOf(
    ServiceOption("Leaky Faucet Repair", R.drawable.bathroom),
    ServiceOption("Blocked Drain", R.drawable.plumber2),
    ServiceOption("Pipe Installation", R.drawable.plumber3),
    ServiceOption("Water Heater Service", R.drawable.plumber4)
)
val electricianOptions = listOf(
    ServiceOption("Leaky Faucet Repair", R.drawable.bathroom),
    ServiceOption("Blocked Drain", R.drawable.plumber2),
    ServiceOption("Pipe Installation", R.drawable.plumber3),
    ServiceOption("Water Heater Service", R.drawable.plumber4)
)
val cleaningOptions = listOf(
    ServiceOption("Leaky Faucet Repair", R.drawable.bathroom),
    ServiceOption("Blocked Drain", R.drawable.plumber2),
    ServiceOption("Pipe Installation", R.drawable.plumber3),
    ServiceOption("Water Heater Service", R.drawable.plumber4)
)
val applianceOptions = listOf(
    ServiceOption("Leaky Faucet Repair", R.drawable.bathroom),
    ServiceOption("Blocked Drain", R.drawable.plumber2),
    ServiceOption("Pipe Installation", R.drawable.plumber3),
    ServiceOption("Water Heater Service", R.drawable.plumber4)
)

@Composable
fun NewRequestScreen(serviceName: String,
                     address: String) {

    val serviceTitle: String
    //val serviceIcon: ImageVector
    val optionsList: List<ServiceOption>

    when (serviceName.lowercase()) {

        "plumber" -> {
            serviceTitle = "Plumber Service"
            //serviceIcon = Icons.Default.Build
            optionsList = plumberOptions
        }

        "electrician" -> {
            serviceTitle = "Electrician Service"
            //serviceIcon = Icons.Default.ElectricalServices
            optionsList = electricianOptions
        }

        "cleaning" -> {
            serviceTitle = "Home Cleaning"
            //serviceIcon = Icons.Default.CleaningServices
            optionsList = cleaningOptions
        }

        "appliance" -> {
            serviceTitle = "Appliance Repair"
            //serviceIcon = Icons.Default.Kitchen
            optionsList = applianceOptions
        }

        else -> {
            serviceTitle = "Service"
            //serviceIcon = Icons.Default.Build
            optionsList = emptyList()
        }
    }

    // 🔥 YAHAN TERA MAIN SCREEN CALL HOGA
    RequestServiceScreen(
        serviceTitle = serviceTitle,
        optionsList = optionsList,
        address = address
    )
}
@Composable
fun RequestServiceScreen(
    serviceTitle: String,
    optionsList: List<ServiceOption>,
    address: String
) {

    var selectedOption by remember { mutableStateOf<ServiceOption?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7F6))
    ) {

        // 🔹 HEADER
        HeaderSection(
            title = serviceTitle
        )

        // 🔹 LOCATION CARD (address pass kar)
        LocationCard(address = address)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 FORM SECTION
            ServiceDetailField()
            Spacer(modifier = Modifier.height(10.dp))

            ProblemDescriptionField()
            Spacer(modifier = Modifier.height(10.dp))

            MediaUploadField()
            Spacer(modifier = Modifier.height(14.dp))

            // 🔹 GRID
            ServiceGrid(
                options = optionsList,
                selectedOption = selectedOption,
                onOptionClick = {
                    selectedOption = it
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // 🔹 BUTTON
            ProceedButton(
                onClick = {
                    // yaha backend ya next screen
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}