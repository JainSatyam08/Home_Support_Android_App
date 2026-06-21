package com.example.homesupport.components.newrequest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.homesupport.R

import com.example.homesupport.screens.BottomNavItem.Category.label
import com.example.homesupport.viewmodel.BookingViewModel

// ── Data model ────────────────────────────────────────────────────────────────
private data class ApplianceOption(
    val id: String,
    //val label: String,
    val imgRes: Int
)

// Define the missing colors

//import androidx.compose.ui.graphics.Color

private object ApplianceItemColors {
    val SelectedBorder = Color(0xFF0F9D58)
    val TextPrimary = Color(0xFF212121)
}



// ── Grid ──────────────────────────────────────────────────────────────────────

/**
 * 2-column grid of appliance service categories.
 * Uses a fixed height to work correctly inside LazyColumn.
 * "Washing Machine" is pre-selected to match the design.
 */
@Composable
fun ApplianceGrid(modifier: Modifier = Modifier,
                  bookingViewModel: BookingViewModel
) {


        val options = when(bookingViewModel.serviceType) {
            "Appliance" -> listOf(
                ApplianceOption("Washing Machine",R.drawable.washingmachine),
                ApplianceOption("Refrigerator",R.drawable.fridge),
                ApplianceOption("Microwave",R.drawable.microwave),
                ApplianceOption("Air  Conditioner",R.drawable.acservice)
            )
            "Plumbing" -> listOf(
                ApplianceOption("Tap Leakage",R.drawable.leakyfaucet),
                ApplianceOption("Blocked Drainage",R.drawable.blockeddrain),
                ApplianceOption("New Pipe Installation",R.drawable.pipeinstall),
                ApplianceOption("Water Heater",R.drawable.waterheater)
            )
            "Cleaning" -> listOf(
                ApplianceOption("Sanitization & Pest Control",R.drawable.deepsanitized),
                ApplianceOption("Standard House Cleaning",R.drawable.standardcleaning),
                ApplianceOption("Kitchen Cleaning",R.drawable.kitchencleaning),
                ApplianceOption("Bathroom Cleaning",R.drawable.bathroom)

            )
            "Electrical" -> listOf(
                ApplianceOption("Switch/MCB Replacement",R.drawable.mcbswitch),
                ApplianceOption("Lighting",R.drawable.lighting),
                ApplianceOption("Installation Sockets/Switch",R.drawable.socketinstall),
                ApplianceOption("Wiring Repair/New Installation",R.drawable.wiringrepair)

            )
            else -> emptyList()
        }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),          // fixed height required inside LazyColumn
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement   = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false     // parent LazyColumn handles scroll
    ) {
        items(options) { option ->
            ApplianceItem(
                //label      = option.label,
                imgRes      = option.imgRes,
                isSelected = bookingViewModel.problemType == option.id,
                onClick    = { bookingViewModel.updateProblemType(option.id) }
            )
        }
    }
}

// ── Single item card ──────────────────────────────────────────────────────────

@Composable
fun ApplianceItem(
    //label: String,
    imgRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) ApplianceItemColors.SelectedBorder else Color(0xFFE0E0E0)
    val bgColor     = if (isSelected) Color(0xFFEAF5F1) else Color.White

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 1.dp
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = borderColor
        ),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Box(modifier=modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = label,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}