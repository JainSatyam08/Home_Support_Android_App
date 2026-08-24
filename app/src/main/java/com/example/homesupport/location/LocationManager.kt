package com.example.homesupport.location

import android.annotation.SuppressLint
import android.content.Context
import android.health.connect.datatypes.ExerciseRoute
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import android.location.Location
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.Priority
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import kotlin.coroutines.resume

class LocationManager(
    private val context: Context
) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getLocation(): LocationData? {

        val location = getCurrentLocation()
            ?: return null

        val address = getAddress(
            latitude = location.latitude,
            longitude = location.longitude
        )

        return LocationData(
            latitude = location.latitude,
            longitude = location.longitude,
            address = address
        )
    }
    private fun getAddress(
        latitude: Double,
        longitude: Double
    ): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1
        )
        return addresses
            ?.firstOrNull()
            ?.getAddressLine(0)
            ?: "Unknown Location"
    }

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): LocationData?{
        val request= CurrentLocationRequest.Builder()
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient
                .getCurrentLocation(request, null)
                .addOnSuccessListener { location ->
                    if(location!=null){
                        val address=getAddress(location.latitude,
                            location.longitude
                        )
                        val locationData=LocationData(
                            location.latitude,
                            location.longitude,
                            address=address
                        )
                        continuation.resume(locationData)
                    }
                    else{
                        continuation.resume(null)
                    }

                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
            }


    }
}