package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.homesupport.components.profile.HeaderSection

@Composable
fun HelpSupportScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
    ) {

        HeaderSection()

        Spacer(modifier = Modifier.height(12.dp))

        SearchBar()

        Spacer(modifier = Modifier.height(12.dp))

        FAQSection()

        Spacer(modifier = Modifier.height(16.dp))

        ContactSection()
    }
}