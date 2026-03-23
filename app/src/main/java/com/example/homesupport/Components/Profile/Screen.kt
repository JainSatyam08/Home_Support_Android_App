package com.example.homesupport.Components.Profile

class Screen {
    sealed class Screen(val route: String) {
        object Settings : Screen("settings")
        object Profile : Screen("profile")
        object SavedAddress : Screen("saved_address")
        object Payment : Screen("payment")
        object Notifications : Screen("notifications")
        object Orders : Screen("orders")
        object Security : Screen("security")
        object Support : Screen("support")
        object About : Screen("about")
    }
}