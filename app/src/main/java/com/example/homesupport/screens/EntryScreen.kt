package com.example.homesupport.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homesupport.EMPLOYEE.screens.EmployeeLoginScreenG
import com.example.homesupport.R

// ---------- Colors (picked from the design) ----------
private val ScreenBg = Color(0xFFF7FAFE)
private val NavyText = Color(0xFF0F2A52)
private val BrandBlue = Color(0xFF2F80D8)
private val GrayText = Color(0xFF4A5B73)

private val UserCardBg = Color(0xFFE3EEFB)
private val UserAccent = Color(0xFF2F80D8)
private val UserAvatarBg = Color(0xFFC9DDF7)

private val PartnerCardBg = Color(0xFFE4F2EE)
private val PartnerAccent = Color(0xFF1F9D6B)
private val PartnerAvatarBg = Color(0xFFC8E6DC)

private val ServiceCardBg = Color(0xFFFDF1E6)
private val ServiceAccent = Color(0xFFF28C28)
private val ServiceAvatarBg = Color(0xFFFCDCBE)

// ---------- Main Screen ----------
@Composable
fun ContinueAsScreenG(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
    ) {
        // Footer is drawn FIRST so the content sits on top of it
        FooterG(modifier = Modifier.align(Alignment.BottomCenter))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Logo + app name + tagline
            HeaderG()

            Spacer(modifier = Modifier.height(12.dp))

            // "Continue as" title
            Text(
                text = "Continue as",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = NavyText
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            Text(
                text = "Choose how you want to access your account\nand get started.",
                fontSize = 12.sp,
                lineHeight = 22.sp,
                color = GrayText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Card 1 - User (blue)
            RoleCardG(
                title = "User",
                description = "Book home services, track\nyour requests and more.",
                avatarRes = R.drawable.userlogo,
                cardColor = UserCardBg,
                avatarBgColor = UserAvatarBg,
                accentColor = UserAccent,
                onClick = { navController.navigate("login") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card 2 - Partner (green)
            RoleCardG(
                title = "Company",
                description = "Access company resources,\nmanage operations and more.",
                avatarRes = R.drawable.technicianlogo,
                cardColor = PartnerCardBg,
                avatarBgColor = PartnerAvatarBg,
                accentColor = PartnerAccent,
                onClick = { navController.navigate("emp_login") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card 3 - Service Partner (orange)
            RoleCardG(
                title = "Service Partner",
                description = "Manage your assigned jobs,\nupdate status and grow your business.",
                avatarRes = R.drawable.technicianlogo,
                cardColor = ServiceCardBg,
                avatarBgColor = ServiceAvatarBg,
                accentColor = ServiceAccent,
                onClick = { navController.navigate("service_partner_login") }
            )
        }
    }
}

// ---------- Header: logo icon + "Home Support" + tagline ----------
@Composable
fun HeaderG() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // House icon
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Home Support logo",
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            // Two-colour app name: "Home" navy + "Support" blue
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = NavyText)) { append("Home ") }
                    withStyle(SpanStyle(color = BrandBlue)) { append("Support") }
                },
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Your Daily Service Partner",
                fontSize = 14.sp,
                color = GrayText
            )
        }
    }
}

// ---------- Reusable role card (avatar + title/description + arrow) ----------
@Composable
fun RoleCardG(
    title: String,
    description: String,
    avatarRes: Int,
    cardColor: Color,
    avatarBgColor: Color,
    accentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardColor)
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circular avatar with tinted background
        Box(
            modifier = Modifier
                .size(84.dp)
                .clip(CircleShape)
                .background(avatarBgColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(18.dp))

        // Title + description take the remaining width
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = NavyText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = GrayText
            )
        }

        // Arrow tinted with the card's accent colour
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Go",
            tint = accentColor,
            modifier = Modifier.size(28.dp)
        )
    }
}

// ---------- Footer: house illustration + handwritten tagline ----------
@Composable
fun FooterG(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        // Bottom illustration (house, trees, hills)
        Image(
            painter = painterResource(id = R.drawable.housebottom),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(150.dp)
                .height(190.dp)
                .padding(20.dp)
        )

        // Slightly tilted handwritten-style tagline on the right
        Text(
            text = "Better Homes\nHappier Lives",
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = BrandBlue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 24.dp, top = 24.dp)
                .rotate(-10f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployeeLoginScreenPreview() {
    ContinueAsScreenG(navController = rememberNavController())
}