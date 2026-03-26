package com.example.homesupport.Components.helpandsupport

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CallSupportDialog(
    onDismiss: () -> Unit,
    onNumberClick: (String) -> Unit
) {

    val numbers = listOf(
        "9876543210",
        "9123456780",
        "9000011111"
    )

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text("Select Number")
        },
        text = {
            Column {
                numbers.forEach { number ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onNumberClick(number)
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "",
                            tint = Color(0xFF43A047)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(text = number)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Close")
            }
        }
    )
}

fun openDialer(context: Context, number: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    context.startActivity(intent)
}