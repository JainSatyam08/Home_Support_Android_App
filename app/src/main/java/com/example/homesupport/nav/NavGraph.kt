package com.example.homesupport.nav

import com.example.homesupport.screens.MyRequests

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.homesupport.screens.LoginScreen

import com.example.homesupport.screens.NewRequestScreen
import com.example.homesupport.screens.ProfileScreen
import com.example.homesupport.screens.SplashScreen
import com.example.homesupport.screens.TrackRequestScreen
import com.example.homesupport.screens.UserDashboard

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ){
        composable("splash"){
            SplashScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("user_dashboard") {
            UserDashboard(navController)
        }
        composable("requests") {
             MyRequests(navController)

        }
        composable("profile") {
            ProfileScreen(navController)

        }
        composable("support") {

        }
        composable(
            route = "service_detail/{serviceType}", // {serviceType} ek variable hai
            arguments = listOf(navArgument("serviceType") { type = NavType.StringType })

        ){
            backStackEntry ->
            val type = backStackEntry.arguments?.getString("serviceType") ?:"Service"
            NewRequestScreen(serviceName=type)

        }

        // NEW REQUEST ROUTE
        /*composable("new_request") {
            NewRequestScreen(navController)
        }*/

        // TRACK REQUEST ROUTE
        composable("track_request") {
            TrackRequestScreen(navController)
        }
    }
}


