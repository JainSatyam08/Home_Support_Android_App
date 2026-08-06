package com.example.homesupport.components.myrequests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homesupport.dto.AllServiceResponse
import com.example.homesupport.viewmodel.AllRequestViewModel


// TODO: Yaha backend/API/DB se data fetch hoga
// TODO: activeList = active requests (status != completed)
// TODO: pastList = completed requests

// TODO: Ye lists ViewModel se aayengi future me


@Composable
fun RequestList(isActive: Boolean,
                requestList: List<AllServiceResponse>,
                nav: NavHostController
) {

    // 🔴 TEMP DATA (Backend ke jagah use ho raha hai)




    // 🔴 Toggle ke hisaab se list select
    val displayList = if (isActive) {
        requestList.filter {
            it.status != "Completed" && it.status != "Cancelled"
        }
    } else {
        requestList.filter {
            it.status == "Completed" || it.status == "Cancelled"
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(displayList.size) { index ->
            RequestCard(displayList[index],nav)
        }
    }
}