package com.example.homesupport.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import android.location.Geocoder
import java.util.Locale

@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    onLocationReceived: (Location?) -> Unit
){
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            // Return location through callback
            onLocationReceived(location)
        }
}

fun getAddressFromLocation(
    context: Context,
    latitude: Double,
    longitude: Double
): String {
    val geocoder = Geocoder(context, Locale.getDefault())

    val addresses = geocoder.getFromLocation(
        latitude,
        longitude,
        1
    )
    return addresses?.get(0)?.getAddressLine(0)
        ?: "Unknown Location"
}