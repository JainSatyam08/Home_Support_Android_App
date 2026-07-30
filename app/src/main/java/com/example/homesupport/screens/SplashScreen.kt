package com.example.homesupport.screens

import android.view.animation.OvershootInterpolator
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.homesupport.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import android.Manifest
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun SplashScreen(nav: NavHostController){
    val scale= remember{
        Animatable(0f)
    }
    val viewModel: SplashViewModel = hiltViewModel()
    val token by viewModel.token.collectAsState(initial = null)
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        val imageGranted =
            permissions[Manifest.permission.READ_MEDIA_IMAGES] == true

        val cameraGranted =
            permissions[Manifest.permission.CAMERA] == true

        // Abhi sirf result mil gaya
        // Baad mein yahan snackbar ya dialog dikha sakte hain
    }
    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )

        )

        delay(1000)
        if(token!=null){
            nav.navigate("user_dashboard") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        }else{
            nav.navigate("login") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        }


    }
    LaunchedEffect(Unit) {

        val hasImagePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED

        val hasCameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        // Agar dono permissions nahi hain to popup dikhao
        if (!hasImagePermission || !hasCameraPermission) {

            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.CAMERA
                )
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Main title animated
            Text(
                text = "Home Support",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4A90E2),
                modifier = Modifier.scale(scale.value) // animated effect
            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Your Daily Service Partner",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        }
    }
}