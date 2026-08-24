package com.example.homesupport.nav




import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.homeservices.ui.cancelrequest.CancelRequestScreen
import com.example.homesupport.location.LocationData
import com.example.homesupport.screens.BookingConfirmedScreen
import com.example.homesupport.screens.CancellationConfirmedScreen


import com.example.homesupport.screens.HelpSupportScreen
import com.example.homesupport.screens.MyRequestDetailScreen

import com.example.homesupport.screens.MyRequests
import com.example.homesupport.screens.NewRequestScreen
import com.example.homesupport.screens.ProfileScreen
import com.example.homesupport.screens.ScheduleServiceScreen
import com.example.homesupport.screens.SignInScreen
import com.example.homesupport.screens.SignUpScreen

import com.example.homesupport.screens.SplashScreen
import com.example.homesupport.screens.TrackRequestScreen
import com.example.homesupport.screens.UserDashboard
import com.example.homesupport.viewmodel.BookingViewModel

@Composable
fun AppNavGraph(navController: NavHostController,
                bookingViewModel: BookingViewModel,
                locationData: LocationData
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ){
        composable("splash"){
            SplashScreen(navController)
        }
        composable("login"){
            SignInScreen(navController)
        }
        composable("signup"){
            SignUpScreen(navController)
        }
        composable("user_dashboard") {
            UserDashboard(navController,locationData)
        }
        composable("requests") {
             MyRequests(navController)

        }
        composable("profile") {
            ProfileScreen(navController)

        }
        composable("support") {
            HelpSupportScreen(navController)

        }
        composable(
            route="new_request/{servicetype}",
            arguments = listOf(navArgument("servicetype"){
                type= NavType.StringType
            })
        ){ backStackEntry ->
            val servicetype = backStackEntry.arguments?.getString("servicetype")
            NewRequestScreen(navController,servicetype,bookingViewModel,locationData)

        }

        composable ("schedulescreen"){
            ScheduleServiceScreen(navController,bookingViewModel)
        }

        composable ("confirmbooking"){
            BookingConfirmedScreen(navController,bookingViewModel)
        }

        // NEW REQUEST ROUTE
        composable("detailScreen/{bookingId}") {
            backStackEntry ->
            val bookingId = backStackEntry.arguments?.getString("bookingId")!!
            MyRequestDetailScreen(navController,bookingViewModel,bookingId)
        }

        // TRACK REQUEST ROUTE
        composable("track_request") {
            TrackRequestScreen(navController)
        }

        composable("cancel_request"){
            CancelRequestScreen(navController)
        }
        composable("cancel_confirm") {
            CancellationConfirmedScreen(navController)
        }



    }
}


