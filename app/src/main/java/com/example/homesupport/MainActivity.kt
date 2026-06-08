package com.example.homesupport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.homesupport.nav.AppNavGraph
import com.example.homesupport.ui.theme.HomeSupportTheme


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeSupportTheme {
                val mainBrush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0B132B), // Deep Blue
                        Color(0xFF1C1C1E)  // Dark Blue/Black
                    )
                )

                val navController = rememberNavController()

                    Scaffold(

                        content = { innerPadding ->

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    //background(mainBrush)
                                    .padding(innerPadding)
                                    //.padding(5.dp)       // your global inner padding
                            ) {
                                AppNavGraph(navController)
                            }
                        }
                    )

            }
        }
    }


}