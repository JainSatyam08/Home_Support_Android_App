package com.example.homesupport.location

import android.content.Context
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.Manifest

class PermissionManager (
    private val context: Context
){
    fun isLocationPermissionGranted(): Boolean{
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )==PackageManager.PERMISSION_GRANTED
    }

}