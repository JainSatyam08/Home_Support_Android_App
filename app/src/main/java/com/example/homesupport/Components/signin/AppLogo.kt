package com.example.homesupport.components.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homesupport.R
import com.example.homesupport.ui.theme.DarkBlue
import com.example.homesupport.ui.theme.HintGray

@Composable
fun AppLogo() {
    // Box → ek container jisme content ko center mein rakh sakte hain
    Box(
        modifier = Modifier
            .size(72.dp)                            // 72x72 dp ka square box
            .clip(RoundedCornerShape(16.dp))        // Corners round karo (slightly rounded square)
            .background(Color(0xFFE8EFF8)),          // Light blue-gray background
        contentAlignment = Alignment.Center          // Andar wala content exactly center mein
    ) {
        // ─ TODO: Jab actual logo drawable ho, neeche wala uncomment karo ─
        Image(
             painter = painterResource(id = R.drawable.logo ),
            contentDescription = "HomeSupport Logo"
        )

        // Abhi ke liye placeholder Lock icon use kar rahe hain

    }
}@Composable
fun SignInTitle() {
    Text(
        text = "Sign In",
        fontSize = 28.sp,               // Bada font — heading size
        fontWeight = FontWeight.Bold,   // Bold weight
        color = DarkBlue,              // Dark blue color (defined above)
        textAlign = TextAlign.Center    // Center align karo
    )
}

@Composable
fun SignUpTitle() {
    Text(
        text       = "Create Account",
        fontSize   = 28.sp,
        fontWeight = FontWeight.Bold,
        color      = DarkBlue,
        textAlign  = TextAlign.Center
    )
}

@Composable
fun SignUpSubtitle() {
    Text(
        text       = "Fill in your details to get started",
        fontSize   = 14.sp,
        color      = HintGray,                    // Gray — secondary text
        textAlign  = TextAlign.Center
    )
}
