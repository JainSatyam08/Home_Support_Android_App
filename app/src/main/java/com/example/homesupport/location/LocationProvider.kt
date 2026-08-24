package com.example.homesupport.location

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationProvider @Inject constructor(
    private val locationCoordinator: LocationCoordinator
) {

    suspend fun getFreshLocation(): LocationResult {
        return locationCoordinator.getLocation()
    }
}