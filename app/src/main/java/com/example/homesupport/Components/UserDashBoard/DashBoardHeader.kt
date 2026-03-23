package com.example.homesupport.Components.UserDashBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.R

@Composable
fun DashboardHeader(){
    Row(
        modifier = Modifier.fillMaxWidth()
            //.padding(16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors=listOf(
                        Color(0xFF1E5F8C),
                        Color(0xFF2F80ED)
                    )
                ),
                shape = RoundedCornerShape(bottomStart = 3.dp, bottomEnd = 3.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
                .height(45.dp)
        ){
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.White.copy(alpha = 0.2f)), // Subtle white background
                contentAlignment = Alignment.Center,

                ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Ensure this is a <vector> now
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize()// Icon is slightly smaller than the box
                )
            }

            // Spacer(modifier = Modifier.width(12.dp))

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Home Support",
                color=Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )



        }
        IconButton(onClick = { /* notification click */ }) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
            )
        }

    }
}