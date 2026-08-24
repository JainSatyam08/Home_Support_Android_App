package com.example.homesupport.components.myrequestdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.homesupport.dto.ServiceDetailResponse
import com.example.homesupport.ui.theme.BackgroundCream
import com.example.homesupport.ui.theme.CardWhite
import com.example.homesupport.ui.theme.DarkGreen
import com.example.homesupport.ui.theme.LightGreenChip
import com.example.homesupport.ui.theme.NavyText
import com.example.homesupport.ui.theme.PrimaryGreen
import com.example.homesupport.ui.theme.SubtleGray
import com.example.homesupport.viewmodel.BookingViewModel

@Composable
fun RequestDetailsBottomBar(
    //totalAmount: String,
    //isPaid: Boolean,
    status:String,
    request: ServiceDetailResponse,
    navController: NavHostController,
    bookingViewModel: BookingViewModel
) {

    Surface(color = BackgroundCream, shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Total Amount", fontSize = 12.sp, color = SubtleGray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    //Text(text = totalAmount, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NavyText)
//                    if (isPaid) {
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Chip(text = "Paid \u2713")
//                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(

                onClick = {
                    if(status.toUpperCase()=="COMPLETED" || status.toUpperCase()=="CANCELLED" || status.toUpperCase()=="CLOSED"){
                        bookingViewModel.prepareForRebooking(request)
                        navController.navigate("schedulescreen")

                    }
                    else{
                        navController.navigate("cancel_request")
                    }

                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                if(status.toUpperCase()=="COMPLETED" || status.toUpperCase()=="CANCELLED" || status.toUpperCase()=="CLOSED"){
                    Text(text = "Book Again", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
                else{
                    Text(text = "Cancel Request", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// REUSABLE UI PRIMITIVES
// ---------------------------------------------------------------------------

@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = CardWhite,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), content = content)
    }
}

@Composable
fun SectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NavyText)
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = LightGreenChip
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = DarkGreen,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}