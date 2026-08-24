package com.example.homesupport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.homesupport.location.LocationGate
import com.example.homesupport.nav.AppNavGraph
import com.example.homesupport.ui.theme.HomeSupportTheme
import com.example.homesupport.viewmodel.BookingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeSupportTheme {
                val navController = rememberNavController()
                val bookingViewModel: BookingViewModel = hiltViewModel()

                Scaffold(
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            LocationGate { locationData ->
                                AppNavGraph(
                                    navController = navController,
                                    bookingViewModel = bookingViewModel,
                                    locationData = locationData
                                )

                            }

                        }
                    }
                )
            }
        }
    }
}
