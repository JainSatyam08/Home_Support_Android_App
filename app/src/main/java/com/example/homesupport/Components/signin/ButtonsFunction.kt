package com.example.homesupport.components.signin

import android.R.attr.onClick
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homesupport.ui.theme.BrandGreen
import com.example.homesupport.ui.theme.DarkBlue
import com.example.homesupport.ui.theme.HintGray
import com.example.homesupport.ui.theme.InputBorder
import com.example.homesupport.ui.theme.LinkBlue

@Composable
fun ContinueButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()     // Puri width le — edge to edge
            .height(52.dp),     // Comfortable tap target size
        shape = RoundedCornerShape(50.dp),  // Pill shape — consistent with input fields
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandGreen     // Green background
        )
    ) {
        Text(
            text = "Continue",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,  // Slightly bold — button text ke liye appropriate
            color = Color.White                // White text on green background
        )
    }
}


@Composable
fun SocialLoginRow(
    //onGoogleClick: () -> Unit,
    //onPhoneClick: () -> Unit
) {
    // Row → dono buttons ko horizontally arrange karta hai
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Buttons ke beech 16dp gap
        verticalAlignment = Alignment.CenterVertically        // Vertically center karo
    ) {
        // Google button — "G" text se (logo nahi, sirf letter)
        SocialIconButton(
            label = "G",                                 // Text-based icon (Google "G")
            contentDescription = "Sign in with Google"
            //onClick = onGoogleClick
        )

        // Phone button — phone icon se
        SocialIconButton(
            icon = Icons.Outlined.Phone,                 // Icon-based button
            contentDescription = "Sign in with Phone"
            //onClick = null
        )
    }
}


// ─────────────────────────────────────────────────────────────────────────────
//  SocialIconButton()
//
//  Ek reusable circular outlined button jo ya toh:
//    - Text label dikhata hai (jaise Google ka "G"), ya
//    - Koi ImageVector icon dikhata hai (jaise phone icon)
//
//  Dono ek hi composable se handle hote hain — zyada flexible hai.
//  Sirf ek parameter pass karo (label ya icon), dusra null chhod do.
//
//  Parameters:
//    label            → Optional: Text dikhana ho toh (e.g., "G")
//    icon             → Optional: Icon dikhana ho toh (e.g., Icons.Outlined.Phone)
//    contentDescription → Accessibility ke liye (screen readers use karte hain)
//    onClick          → Button press hone ka callback
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun SocialIconButton(
    label: String? = null,          // Default null — matlab text nahi dikhana
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null, // Default null — icon nahi dikhana
    contentDescription: String,
    //onClick: () -> Unit
) {
    // OutlinedButton → border wala button, koi fill nahi
    OutlinedButton(
        onClick = {},
        modifier = Modifier.size(52.dp),        // 52x52 dp — square, phir circle shape se clip hoga
        shape = CircleShape,                    // Perfect circle shape
        contentPadding = PaddingValues(0.dp),   // Default padding hata do — content center mein rahe
        border = BorderStroke(1.5.dp, InputBorder), // Light gray border — thin aur subtle
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White        // White background
        )
    ) {
        // Agar label provide kiya hai toh text dikhao (Google "G" ke liye)
        if (label != null) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue               // Dark blue "G" text
            )
        }
        // Agar icon provide kiya hai toh icon dikhao (Phone ke liye)
        else if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = HintGray,               // Gray icon
                modifier = Modifier.size(22.dp)
            )
        }
        // Note: label aur icon dono null nahi hone chahiye — ek toh provide karo
    }
}

@Composable
fun RegisterButton(onClick: () -> Unit) {
    Button(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape  = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
    ) {
        Text(
            text       = "Create Account",
            fontSize   = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color      = Color.White
        )
    }
}

@Composable
fun AlreadyHaveAccountPrompt(nav: NavHostController) {

    val annotatedText = buildAnnotatedString {
        append("Already have an account? ")

        withStyle(
            style = SpanStyle(
                color      = LinkBlue,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append("Sign In")
        }
    }

    Text(
        text     = annotatedText,
        fontSize = 14.sp,
        color    = Color(0xFF555555),
        modifier = Modifier.clickable { nav.popBackStack() }
    )
}

@Composable
fun SignUpPrompt(nav: NavHostController) {

    // buildAnnotatedString → mixed styling wala string banata hai
    val annotatedText = buildAnnotatedString {
        // Normal gray text — no special style
        append("New to the app? ")

        // "Sign Up" ke liye alag style — blue aur bold
        withStyle(
            style = SpanStyle(
                color = LinkBlue,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append("Sign Up")
        }
    }

    // Pura text ek hi Text composable mein — clickable banana ke liye Modifier lagao
    Text(
        text = annotatedText,
        fontSize = 14.sp,
        color = Color(0xFF555555),              // Dark gray for "New to the app?" part
        modifier = Modifier.clickable { nav.navigate("signup") } // Anywhere click karo toh callback
    )
}