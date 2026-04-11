package com.example.homesupport.components.newrequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProblemDescriptionField() {

    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // 🔹 Label
        Text(
            text = "Problem Description",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(6.dp))

        // 🔹 Multi-line Input Box
        Card(
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp) // 👈 बड़ा box
        ) {

            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(14.dp)
            ) {

                if (description.isEmpty()) {
                    Text(
                        text = "Describe your problem in detail...",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }

                BasicTextField(
                    value = description,
                    onValueChange = { description = it },

                    modifier = Modifier.fillMaxSize(),
                    maxLines = 5 // 👈 multi-line
                )
            }
        }
    }
}