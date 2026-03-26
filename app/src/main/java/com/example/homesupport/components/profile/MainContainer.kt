package com.example.homesupport.components.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainCardSection() {
    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        SettingsContent()
    }
}

@Composable
fun SettingsContent() {
    Column(modifier = Modifier.padding(16.dp)) {

        ProfileSection()

        SectionDivider()

        SettingsMenu()

        LogoutSection()
    }
}