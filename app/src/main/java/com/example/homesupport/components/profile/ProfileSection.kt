package com.example.homesupport.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileImage()

        Spacer(modifier = Modifier.width(12.dp))

        ProfileText()
    }
}
@Composable
fun ProfileImage() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(Color.LightGray, CircleShape)
    )
}
@Composable
fun ProfileText() {
    Column {
        Text(
            text = "User Name",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "Edit Profile",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SectionDivider() {
    Divider(modifier = Modifier.padding(vertical = 12.dp))
}