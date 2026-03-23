package com.example.homesupport.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route:String,val label:String,val icon: ImageVector){
    object Home:BottomNavItem("home","Home", Icons.Default.Home)
    object History:  BottomNavItem("history","History", Icons.Default.History)
    object Feedback:  BottomNavItem("feedback","FeedBack", Icons.Default.Feedback)
    object Category:  BottomNavItem("category","Category", Icons.Default.Category)



}