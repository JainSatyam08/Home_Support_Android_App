package com.example.homesupport.components.myrequestdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.homesupport.ui.theme.DividerGray
import com.example.homesupport.ui.theme.NavyText
import com.example.homesupport.ui.theme.SubtleGray
import com.example.homesupport.ui.theme.PeachButton

@Composable
fun CustomerDetailsSection(
    name: String,
    phone: String,
    email: String,
    onContactClick: () -> Unit
) {
    SectionCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                SectionHeader(icon = Icons.Outlined.Person, title = "Customer Details")
            }
            Surface(
                shape = RoundedCornerShape(50),
                color = PeachButton,
                modifier = Modifier.clickable(onClick = onContactClick)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = null,
                        tint = NavyText,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Contact", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NavyText)
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        CustomerDetailRow(icon = Icons.Outlined.Person, label = "Name", value = name)
        Spacer(modifier = Modifier.height(8.dp))
        CustomerDetailRow(icon = Icons.Outlined.Call, label = "Phone", value = phone)
        Spacer(modifier = Modifier.height(8.dp))
        CustomerDetailRow(icon = Icons.Outlined.Email, label = "Email", value = email)
    }
}

@Composable
fun CustomerDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = SubtleGray, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = label, fontSize = 13.sp, color = SubtleGray, modifier = Modifier.width(60.dp))
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NavyText)
    }
}

// ---------------------------------------------------------------------------
// PROBLEM DESCRIPTION SECTION
// ---------------------------------------------------------------------------

@Composable
fun ProblemDescriptionSection(
    description: String,
    extraPhotoCount: Int
) {
    SectionCard {
        SectionHeader(icon = Icons.Outlined.Description, title = "Problem Description")
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.Top) {
            Text(
                text = description,
                fontSize = 13.sp,
                color = NavyText,
                lineHeight = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(2) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFD8CFC4))
                    )
                }
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, DividerGray, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "+$extraPhotoCount", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = NavyText)
                }
            }
        }
    }
}
