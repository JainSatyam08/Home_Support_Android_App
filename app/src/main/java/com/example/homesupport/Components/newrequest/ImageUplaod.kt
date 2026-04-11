package com.example.homesupport.components.newrequest

@Composable
// WE CAN INTEGRATE HERE SO THAT WE CLICK THIS CARD AND
fun MediaUploadField() {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // 🔹 Label
        Text(
            text = "Upload Image / Video",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(6.dp))

        // 🔹 Upload Box
        Card(
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.CloudUpload,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Tap to upload image or video",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}