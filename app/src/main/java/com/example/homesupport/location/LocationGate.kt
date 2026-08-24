package com.example.homesupport.location

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat


@Composable
fun LocationGate(
    content: @Composable (LocationData) -> Unit
) {

    val context = LocalContext.current

    val coordinator = remember {
        LocationCoordinator(context)
    }

    var locationResult by remember {
        mutableStateOf<LocationResult?>(null)
    }

    var isFetchingLocation by remember {
        mutableStateOf(false)
    }

    var permissionDenied by remember {
        mutableStateOf(false)
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {

                permissionDenied = false
                isFetchingLocation = true

            } else {

                // Abhi custom dialog nahi dikhayenge
                permissionDenied = true
            }
        }

    // App start
    LaunchedEffect(Unit) {

        val permissionGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {

            isFetchingLocation = true

        } else {

            permissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    // Fresh location fetch
    LaunchedEffect(isFetchingLocation) {

        if (isFetchingLocation) {

            locationResult = coordinator.getLocation()

            isFetchingLocation = false
        }
    }

    when {

        locationResult is LocationResult.Success -> {

            val result =
                locationResult as LocationResult.Success

            content(result.location)
        }

        isFetchingLocation -> {

            LocationLoadingDialog()
        }

        permissionDenied -> {

            /*
             * Abhi kuch custom nahi.
             * Permission ke bina app aage nahi jayega.
             */

            PermissionDeniedScreen(
                onRetry = {
                    permissionDenied = false

                    permissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
            )
        }

        locationResult is LocationResult.LocationUnavailable -> {

            Text("Unable to fetch location")
        }

        else -> {

            LocationLoadingDialog()
        }
    }
}

/*
 * =========================================================
 * PERMISSION REQUIRED DIALOG
 * =========================================================
 */

@Composable
fun LocationPermissionDialog(
    onAllowLocation: () -> Unit
) {

    AlertDialog(

        onDismissRequest = {
            // Permission mandatory hai
        },

        title = {

            Text(
                text = "Location Permission Required"
            )
        },

        text = {

            Text(
                text = "Location permission is required to continue using Home Support."
            )
        },

        confirmButton = {

            Button(
                onClick = onAllowLocation
            ) {

                Text(
                    text = "Allow Location"
                )
            }
        }
    )
}


/*
 * =========================================================
 * LOCATION LOADING DIALOG
 * =========================================================
 */

@Composable
fun LocationLoadingDialog() {

    Dialog(
        onDismissRequest = {}
    ) {

        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {

            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CircularProgressIndicator()

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Text(
                    text = "Fetching your location..."
                )
            }
        }
    }
}


/*
 * =========================================================
 * LOCATION UNAVAILABLE DIALOG
 * =========================================================
 */

@Composable
fun LocationUnavailableDialog(
    onRetry: () -> Unit
) {

    AlertDialog(

        onDismissRequest = {},

        title = {

            Text(
                text = "Location Unavailable"
            )
        },

        text = {

            Text(
                text = "Unable to fetch your current location."
            )
        },

        confirmButton = {

            Button(
                onClick = onRetry
            ) {

                Text(
                    text = "Retry"
                )
            }
        }
    )
}

@Composable
fun PermissionDeniedScreen(
    onRetry: () -> Unit
) {

    androidx.compose.material3.AlertDialog(
        onDismissRequest = {},

        title = {
            Text("Location Required")
        },

        text = {
            Text(
                "Go to Settings and allow location permission to continue."
            )
        },

        confirmButton = {

            androidx.compose.material3.Button(
                onClick = onRetry
            ) {
                Text("Try Again")
            }
        }
    )
}