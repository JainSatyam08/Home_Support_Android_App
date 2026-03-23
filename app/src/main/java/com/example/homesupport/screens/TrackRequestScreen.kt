package com.example.homesupport.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun TrackRequestScreen(nav: NavHostController) {
    Text("Track Request Screen", modifier = Modifier.padding(20.dp), fontSize = 22.sp)
}