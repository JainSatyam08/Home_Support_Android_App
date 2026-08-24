package com.example.homesupport.location

sealed class LocationResult {
    data class Success(
        val location: LocationData
    ) : LocationResult()
    data object PermissionRequired : LocationResult()
    data object LocationUnavailable : LocationResult()
}