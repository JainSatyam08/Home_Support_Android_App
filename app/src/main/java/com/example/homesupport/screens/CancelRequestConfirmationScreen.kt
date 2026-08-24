package com.example.homesupport.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.components.cancelconfirmscreen.AlwaysHereCard
import com.example.homesupport.components.cancelconfirmscreen.CancellationDetailsCard
import com.example.homesupport.components.cancelconfirmscreen.ConfirmationTopBar
import com.example.homesupport.components.cancelconfirmscreen.GoToHomeButton
import com.example.homesupport.components.cancelconfirmscreen.NeedHelpCard
import com.example.homesupport.components.cancelconfirmscreen.SuccessHeader
import com.example.homesupport.ui.theme.SoftGrayBg

/* ---------------------------------------------------------
 * Color tokens (kept local to this screen; move to Theme.kt
 * if you want them shared app-wide)
 * --------------------------------------------------------- */


/* ---------------------------------------------------------
 * Data model for a single cancellation-detail row
 * --------------------------------------------------------- */
data class CancellationDetail(
    val icon: ImageVector,
    val label: String,
    val value: String
)

/* ---------------------------------------------------------
 * MAIN SCREEN — only NavHostController is passed in, per
 * the project's architectural pattern. Swap the hardcoded
 * values below for ViewModel + collectAsState() when wiring
 * up real data.
 * --------------------------------------------------------- */
@Composable
fun CancellationConfirmedScreen(navController: NavHostController) {
    val details = listOf(
        CancellationDetail(Icons.Filled.Description, "Request ID", "HSPLB250512001"),
        CancellationDetail(Icons.Filled.WaterDrop, "Service", "Plumbing"),
        CancellationDetail(Icons.Filled.CalendarToday, "Cancelled On", "12 May 2025, 10:30 AM"),
        CancellationDetail(Icons.Filled.PersonOutline, "Cancelled By", "Satyam Jain"),
        CancellationDetail(Icons.Filled.ChatBubbleOutline, "Reason", "I don't need the service now.")
    )

    Scaffold(
        containerColor = SoftGrayBg,
        topBar = {
            ConfirmationTopBar(onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            SuccessHeader(
                title = "Your request has been cancelled successfully!",
                subtitle = "We're sorry to see you go.\nThank you for using Home Support."
            )

            Spacer(modifier = Modifier.height(8.dp))

            CancellationDetailsCard(details = details)

            Spacer(modifier = Modifier.height(14.dp))

            NeedHelpCard(
                onClick = {
                    // TODO: navController.navigate("support")
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            AlwaysHereCard(
                onBookAgainClick = {
                    // TODO: navController.navigate("book_request")
                }
            )

            Spacer(modifier = Modifier.height(18.dp))

            GoToHomeButton(
                onClick = {
                    // TODO: navController.navigate("home") { popUpTo(0) }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


