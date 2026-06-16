package com.example.homesupport.components.newrequest.bookingconfirm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.screens.BookingTheme

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = BookingTheme.CardWhite,
        tonalElevation = 4.dp
    ) {
        val items = listOf(
            Triple(Icons.Default.Home,        "Home",     true),
            Triple(Icons.Default.CheckCircle, "Bookings", false),
            Triple(Icons.Default.Notifications,"Alerts",  false),
            Triple(Icons.Default.Person,      "Profile",  false),
        )
        items.forEach { (icon, label, selected) ->
            NavigationBarItem(
                selected = selected,
                onClick = { /* route */ },
                icon = {
                    Icon(imageVector = icon, contentDescription = label)
                },
                label = { Text(label, fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BookingTheme.TealPrimary,
                    selectedTextColor = BookingTheme.TealPrimary,
                    indicatorColor    = BookingTheme.TealPrimary.copy(alpha = 0.12f),
                    unselectedIconColor = BookingTheme.TextSecondary,
                    unselectedTextColor = BookingTheme.TextSecondary
                )
            )
        }
    }
}