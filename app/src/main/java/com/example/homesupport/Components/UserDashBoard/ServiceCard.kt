package com.example.homesupport.Components.UserDashBoard

import android.net.nsd.NsdManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.R





@Composable
fun ServiceCard(
    title: String,
    imgRes: Int,
    onClick: () -> Unit

){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .height(160.dp),
        shape= RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = onClick
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFDFCFB),
                            Color(0xFFE2D1C3),
                        )
                    ),
                    shape = RoundedCornerShape(16.dp) // ⚠️ SAME shape
                )
                .padding(6.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imgRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape) // 👉 makes it circular
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFA03191F)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServiceGrid(navController: androidx.navigation.NavHostController) {
    val services = listOf(
        Triple("Plumbing", R.drawable.plumber,"plumbing"),
        Triple("Electrical",R.drawable.electrician,"electrical"),
        Triple("Cleaning",R.drawable.cleaning,"cleaning"),
        Triple("Appliance",R.drawable.appliancecare,"appliance")

    )
    Column(
        modifier=Modifier
            .fillMaxWidth()
            .padding(5.dp),
            //.height(350.dp)
            //.verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        // Chunk the list into pairs (2 items per row)
        val rows = services.chunked(2)

        for (rowItems in rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (service in rowItems) {
                    // weight(1f) ensures both cards take equal width in the row
                    Box(modifier = Modifier.weight(1f)) {
                        ServiceCard(
                            title = service.first,
                            imgRes = service.second,
                            onClick = { /* Handle Click */ }
                        )
                    }
                }

                // If a row has only 1 item, add an empty Spacer to keep the alignment
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
    /*LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                color = Color.White
            ), // control height
        contentPadding = PaddingValues(8.dp)
    ) {
        items(services.size) { index ->
            val service = services[index]
            ServiceCard(
                title = service.first,
                imgRes = service.second,
                onClick = { }
            )
        }
    }*/
}
