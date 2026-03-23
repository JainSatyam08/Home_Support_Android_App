package com.example.homesupport.permission

import androidx.compose.runtime.Composable

import android.Manifest
import androidx.compose.runtime.*
import com.google.accompanist.permissions.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionHandler(
    onPermissionGranted: () -> Unit,
){
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(permissionState.status) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    if (permissionState.status.isGranted) {
        onPermissionGranted()
    }
}
