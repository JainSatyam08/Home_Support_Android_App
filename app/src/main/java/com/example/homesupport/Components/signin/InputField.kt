package com.example.homesupport.components.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.ui.theme.DarkBlue
import com.example.homesupport.ui.theme.HintGray
import com.example.homesupport.ui.theme.InputBorder
import com.example.homesupport.ui.theme.LinkBlue

@Composable
fun EmailInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,                              // Current text value
        onValueChange = onValueChange,              // Text change hone pe callback
        modifier = Modifier.fillMaxWidth(),         // Puri width le lo
        placeholder = {
            // Jab field empty ho toh ye gray text dikhta hai
            Text(text = "Email Address", color = HintGray)
        },
        leadingIcon = {
            // Field ke left side pe envelope icon
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = "Email Icon",      // Accessibility ke liye description
                tint = DarkBlue,                        // Icon ka color — dark blue
                modifier = Modifier.size(24.dp)         // Standard icon size
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email       // Email keyboard — @ symbol convenient hota hai
        ),
        singleLine = true,                          // Multiline nahi — sirf ek line
        shape = RoundedCornerShape(50.dp),          // Pill/capsule shape ke liye
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = InputBorder,     // Default border — light gray
            focusedBorderColor   = DarkBlue,        // Focus me border — dark blue highlight
            cursorColor          = DarkBlue         // Text cursor bhi dark blue
        )
    )
}
@Composable
fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }  // Default: hidden (false)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password", color = HintGray)
        },
        leadingIcon = {
            // Left side pe lock icon
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password icon",
                tint = HintGray
            )
        },
        trailingIcon = {
            // Right side pe Show/Hide toggle button
            // TextButton → background/border nahi hota, sirf text
            TextButton(onClick = { passwordVisible = !passwordVisible }) { // Toggle karo
                Text(
                    text = if (passwordVisible) "Hide" else "Show", // State ke hisaab se text change
                    fontSize = 12.sp,
                    color = LinkBlue   // Blue color — link jaisa dikhta hai
                )
            }
        },
        // passwordVisible = true  → VisualTransformation.None = plain text dikhao
        // passwordVisible = false → PasswordVisualTransformation = dots (••••) dikhao
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password    // Password keyboard type
        ),
        singleLine = true,
        shape = RoundedCornerShape(50.dp),          // Same pill shape as email field
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = InputBorder,
            focusedBorderColor   = DarkBlue,
            cursorColor          = DarkBlue
        )
    )
}


@Composable
fun AuthInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    iconDescription: String,
    keyboardType: KeyboardType = KeyboardType.Text,

) {
    Column(modifier = Modifier.fillMaxWidth()) {  // Column taaki error neeche aa sake

        OutlinedTextField(
            value         = value,
            onValueChange = onValueChange,
            modifier      = Modifier.fillMaxWidth(),
            placeholder   = { Text(text = placeholder, color = HintGray) },
            leadingIcon   = {
                Icon(
                    imageVector     = leadingIcon,
                    contentDescription = iconDescription,
                    tint            =  HintGray
                    // Error ho toh icon bhi red ho jaata hai — visual cue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine    = true,
            //isError       = errorMessage != null, // true hone pe border red ho jaata hai
            shape         = RoundedCornerShape(50.dp),
            colors        = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = InputBorder,
                focusedBorderColor   = DarkBlue,
                errorBorderColor     = Red,  // Error state mein border red
                cursorColor          = DarkBlue
            )
        )


    }
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    // Local toggle state — sirf is field ko pata hona chahiye
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            value         = value,
            onValueChange = onValueChange,
            modifier      = Modifier.fillMaxWidth(),
            placeholder   = { Text(text = placeholder, color = HintGray) },
            leadingIcon   = {
                Icon(
                    imageVector        = Icons.Outlined.Lock,
                    contentDescription = "Password icon",
                    tint               = HintGray
                )
            },
            trailingIcon  = {
                // Show/Hide toggle button
                TextButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(
                        text      = if (passwordVisible) "Hide" else "Show",
                        fontSize  = 12.sp,
                        color     = LinkBlue
                    )
                }
            },
            // passwordVisible true → plain text, false → dots
            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine    = true,
            //isError       = errorMessage != null,   // Border red ho jaayega error pe
            shape         = RoundedCornerShape(50.dp),
            colors        = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = InputBorder,
                focusedBorderColor   = DarkBlue,
                //errorBorderColor     = ErrorRed,
                cursorColor          = DarkBlue
            )
        )

        // Error message neeche dikhao agar hai toh

    }
}
