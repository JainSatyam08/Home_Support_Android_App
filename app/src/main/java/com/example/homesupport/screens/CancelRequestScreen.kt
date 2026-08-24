package com.example.homeservices.ui.cancelrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.components.cancelrequest.CancelRequestButton
import com.example.homesupport.components.cancelrequest.CancelRequestTopBar
import com.example.homesupport.components.cancelrequest.CancellationPolicyCard
import com.example.homesupport.components.cancelrequest.CancellationReasonSection
import com.example.homesupport.components.cancelrequest.RequestInfoCard
import com.example.homesupport.components.cancelrequest.SecureFooterText
import com.example.homesupport.ui.theme.SoftGrayBg

/* ---------------------------------------------------------
 * Color tokens (kept local to this screen; move to Theme.kt
 * if you want them shared app-wide)
 * --------------------------------------------------------- */


/* ---------------------------------------------------------
 * Data model for a cancellation reason option
 * --------------------------------------------------------- */
data class CancelReasonOption(
    val id: String,
    val label: String
)

private val cancelReasonOptions = listOf(
    CancelReasonOption("change_of_mind", "Change of mind"),
    CancelReasonOption("found_another", "Found another service provider"),
    CancelReasonOption("booked_by_mistake", "Booked by mistake"),
    CancelReasonOption("not_required", "Service no longer required"),
    CancelReasonOption("other", "Other (Please specify)")
)

/* ---------------------------------------------------------
 * MAIN SCREEN — only NavHostController is passed in, per
 * the project's architectural pattern. All other state is
 * owned locally (swap for ViewModel + collectAsState() when
 * wiring up real data).
 * --------------------------------------------------------- */
@Composable
fun CancelRequestScreen(navController: NavHostController) {
    var selectedReasonId by remember { mutableStateOf("other") }
    var otherReasonText by remember { mutableStateOf("I don't need the service now.") }
    val maxReasonLength = 150

    Scaffold(
        containerColor = SoftGrayBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {
            CancelRequestTopBar(
                onBackClick = { navController.popBackStack() }
            )

            RequestInfoCard(
                requestId = "HSPLB250512001",
                serviceName = "Plumbing",
                serviceIcon = Icons.Filled.WaterDrop
            )

            Spacer(modifier = Modifier.height(20.dp))

            CancellationReasonSection(
                options = cancelReasonOptions,
                selectedReasonId = selectedReasonId,
                onReasonSelected = { selectedReasonId = it },
                otherReasonText = otherReasonText,
                onOtherReasonChanged = {
                    if (it.length <= maxReasonLength) otherReasonText = it
                },
                maxReasonLength = maxReasonLength
            )

            Spacer(modifier = Modifier.height(16.dp))

            CancellationPolicyCard()

            Spacer(modifier = Modifier.height(20.dp))

            CancelRequestButton(
                enabled = selectedReasonId != "other" || otherReasonText.isNotBlank(),
                onClick = {
                    // TODO: hook up ViewModel action, e.g. viewModel.cancelRequest(...)
                    navController.navigate("cancel_confirm")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            SecureFooterText()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}