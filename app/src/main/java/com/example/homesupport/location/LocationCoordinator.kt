package com.example.homesupport.location

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class LocationCoordinator @Inject constructor (
    @ApplicationContext private val context: Context
) {
    private val locationManager = LocationManager(context)
    private val permissionManager = PermissionManager(context)

    suspend fun getLocation():LocationResult {
        if (!permissionManager.isLocationPermissionGranted()) {
            return LocationResult.PermissionRequired
        }
        val locationData = locationManager.getCurrentLocation()
        return if (locationData != null) {
            LocationResult.Success(locationData)
        } else {
            LocationResult.LocationUnavailable
        }
    }

}