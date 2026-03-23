package com.example.homesupport.Components.UserDashBoard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, Screen.Home.route),
    BottomNavItem("My Requests", Icons.Default.List, Screen.Requests.route),
    BottomNavItem("Profile", Icons.Default.Person, Screen.Profile.route),
    BottomNavItem("Support", Icons.Default.Info, Screen.Support.route)
)

@Composable
fun BottomBar(navController: NavHostController) {

    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {

        bottomNavItems.forEach { item ->

            NavigationBarItem(
                selected = currentRoute == item.route,

                onClick = {
                    navController.navigate(item.route) {

                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(item.icon, contentDescription = item.label)
                },

                label = {
                    Text(item.label)
                }
            )
        }
    }
}