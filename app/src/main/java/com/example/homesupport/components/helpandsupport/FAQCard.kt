package com.example.homesupport.components.helpandsupport

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FAQItem(
    val title: String,
    //val answer: String,
    val isExpanded: Boolean = false
)

@Composable
fun FAQSection() {

    val faqList = remember {
        mutableStateListOf(
            FAQItem("Booking Issues"),
            FAQItem("Payment Queries"),
            FAQItem("Partner Verification"),
            FAQItem("Cancel Request")
        )
    }

    Column {
        faqList.forEachIndexed { index, item ->
            FAQCard(
                item = item,
                onClick = {
                    faqList[index] = item.copy(isExpanded = !item.isExpanded)
                }
            )
        }
    }
}
@Composable
fun FAQCard(item: FAQItem, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = Color(0xFF00897B)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = if (item.isExpanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }

        if (item.isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.title,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
