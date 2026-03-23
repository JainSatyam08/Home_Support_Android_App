package com.example.homesupport.Components.UserDashBoard

sealed class Screen(val route: String) {
    object Home : Screen("user_dashboard")
    object Requests : Screen("requests")
    object Profile : Screen("profile")
    object Support : Screen("support")
}
