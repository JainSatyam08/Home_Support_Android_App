package com.example.homesupport.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.NavHostController
import com.example.homesupport.components.UserDashBoard.BottomBar
import com.example.homesupport.components.UserDashBoard.LocationBar   // ← your existing one
import com.example.homesupport.components.newrequest.*
import com.example.homesupport.location.getAddressFromLocation
import com.example.homesupport.location.getCurrentLocation
import com.example.homesupport.permission.LocationPermissionHandler
import com.example.homesupport.viewmodel.BookingViewModel

import android.Manifest


import android.util.Log
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.IconButton
import androidx.core.content.FileProvider
import androidx.core.os.postDelayed
import coil.compose.AsyncImage
import java.io.File

import android.os.Handler
import android.os.Looper


@Composable

fun NewRequestScreen(nav: NavHostController, serviceType: String?,
                     bookingViewModel: BookingViewModel) {
    //val bookingViewModel: BookingViewModel = viewModel()
    LaunchedEffect(serviceType) {
        bookingViewModel.updateServiceType(serviceType ?: "")
    }
    RequestContent(nav = nav, modifier = Modifier,serviceType,bookingViewModel=bookingViewModel)
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RequestContent(nav: NavHostController,
                     modifier: Modifier,
                   serviceType: String?,
                   bookingViewModel: BookingViewModel) {

    //val bookingViewModel: BookingViewModel = viewModel()

    var permissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var cameraImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var showMediaOptions by remember {
        mutableStateOf(false)
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->

        if (success && cameraImageUri != null) {
            bookingViewModel.addMedia(cameraImageUri!!)
        }
    }
    fun openCamera() {

        val imageFile = File(
            context.cacheDir,
            "camera_${System.currentTimeMillis()}.jpg"
        )

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )

        cameraImageUri = uri
        cameraLauncher.launch(uri)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ){uris: List<Uri> ->
        Log.d("PHOTO", "Selected: ${uris.size}")

        bookingViewModel.addMedia(uris)
    }

    val imagePermissionLauncher=rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){granted->
        if(granted){
            galleryLauncher.launch("image/*")
        }

    }

    var address by remember { mutableStateOf("Fetching location...") }



    Box(modifier = modifier.fillMaxSize()) {
        // 1. Dashboard Header at the top layer background
        Header(serviceType=serviceType)

        // 2. Main content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp) // Adjust based on header height
        ) {
            // Location Permission logic (Invisible)
            LocationPermissionHandler {
                permissionGranted = true
                getCurrentLocation(context) { location ->
                    location?.let {
                        address = getAddressFromLocation(
                            context,
                            it.latitude,
                            it.longitude
                        )
                        bookingViewModel.updateaddress(address);
                        bookingViewModel.updateLocation(
                            it.latitude,
                            it.longitude
                        )
                    }
                }
            }

            // Location Bar - Spans full width
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (permissionGranted) {
                    LocationBar(address)
                } else {
                    Text(
                        text = "Location permission required",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp)) // Added space after LocationBar

            // The rest of the content with white background starting here
            LazyColumn(
                modifier = Modifier
                    //.fillMaxSize()
                    .weight(1f)
                    .offset(y = (-29).dp) // Reduced negative offset to move section down
                    .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(top = 25.dp, start = 16.dp, end = 16.dp) // Increased top padding to move search bar down
            ) {
                item {
                    Text(
                        text = "Service Details",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF1A1A1A)

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                //Spacer(50.dp)

                // 3b. Problem Description field
                item {
                    InputBox(placeholder = "Problem Description",
                        bookingViewModel=bookingViewModel)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // 3c. Photos / Video row
                item {
                    PhotosVideoRow(
                        onAddPhoto = {
                            //Log.d("PHOTO", "Launching Gallery")
                            //galleryLauncher.launch("image/*")
                            showMediaOptions = true
                            //openCamera()

                            /*val hasPermission = ContextCompat.checkSelfPermission(
                            context,
                                Manifest.permission.READ_MEDIA_IMAGES

                            )==PackageManager.PERMISSION_GRANTED
                            if (hasPermission){
                                galleryLauncher.launch("image/*")
                            }
                            else {
                                imagePermissionLauncher.launch(
                                    Manifest.permission.READ_MEDIA_IMAGES
                                )
                            }
                            */
                             */

                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item{
                    if(bookingViewModel.selectMediaUris.isNotEmpty()){
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(bookingViewModel.selectMediaUris){uris->
                                Box{
                                    AsyncImage(
                                        model = uris,
                                        contentDescription = "selected image",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(RoundedCornerShape(12.dp)),
                                        contentScale = ContentScale.Crop

                                    )
                                    IconButton(onClick = {
                                        bookingViewModel.removeMedia(uris)

                                    },modifier = Modifier.align(Alignment.TopEnd)) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Remove",
                                            tint = Color.White
                                        )
                                    }

                                }

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // 3d. Appliance category grid
                item {
                    ApplianceGrid(
                        bookingViewModel=bookingViewModel)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // 3e. Proceed button
                item {
                    Text(
                        text = "Selected Photos: ${bookingViewModel.selectMediaUris.size}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    ProceedButton(navController = nav)
                }
            }

            BottomBar(navController = nav)

        }
        if (showMediaOptions) {

            ModalBottomSheet(
                onDismissRequest = {
                    showMediaOptions = false
                }
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Camera option
                    Button(
                        onClick = {
                            showMediaOptions = false
                            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                openCamera()
                            }, 300)

                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Take Photo")
                    }

                    // Gallery option
                    OutlinedButton(
                        onClick = {
                            showMediaOptions = false
                            Handler(Looper.getMainLooper()).postDelayed({
                                galleryLauncher.launch("image/*")
                            }, 300)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.PhotoLibrary, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Choose from Gallery")
                    }

                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

