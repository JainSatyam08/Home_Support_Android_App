package com.example.homesupport.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homesupport.components.helpandsupport.CallSupportDialog
import com.example.homesupport.components.helpandsupport.ContactSection
import com.example.homesupport.components.helpandsupport.FAQSection
import com.example.homesupport.components.helpandsupport.HeaderSection
import com.example.homesupport.components.helpandsupport.SearchBar
import com.example.homesupport.components.helpandsupport.openDialer

@Composable
fun HelpSupportScreen(navController: NavHostController) {
    val searchText = remember { mutableStateOf("") }

    val faqList = listOf(
        "Booking Issues",
        "Payment Queries",
        "Partner Verification",
        "Cancel Request"
    )

    val filteredList = faqList.filter {
        it.contains(searchText.value, ignoreCase = true)
    }

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
    ) {

        HeaderSection(
            onBackClick = {
                navController.popBackStack()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SearchBar(
            searchText = searchText.value,
            onSearchChange = {
                searchText.value = it
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        FAQSection()

        Spacer(modifier = Modifier.height(16.dp))

        ContactSection(
            onCallClick = {
                showDialog = true
            },
            onChatClick = { }
        )
        if (showDialog) {
            CallSupportDialog(
                onDismiss = {
                    showDialog = false
                },
                onNumberClick = { number ->
                    showDialog = false
                    openDialer(context, number)
                }
            )
        }
    }
}