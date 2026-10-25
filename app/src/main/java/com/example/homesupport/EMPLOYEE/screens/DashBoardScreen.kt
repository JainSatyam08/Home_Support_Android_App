package com.example.homesupport.EMPLOYEE.screens

 // TODO: apne package name se replace kar
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homesupport.EMPLOYEE.viewmodel.DashBoardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


/* ------------------------------------------------------------------ */
/*  Dependencies chahiye:                                              */
/*  - androidx.compose.material:material-icons-extended                */
/*  - androidx.lifecycle:lifecycle-viewmodel-compose                   */
/*  - androidx.navigation:navigation-compose                           */
/* ------------------------------------------------------------------ */

// ===================== COLORS =====================
private val NavyG = Color(0xFF0F2A5C)
private val SubtleTextG = Color(0xFF5B6B8C)
private val BlueG = Color(0xFF2F80ED)
private val ScreenBgG = Color(0xFFFAFBFF)
private val CardBorderG = Color(0xFFE3EAF6)

// ===================== MODELS =====================

// Overview ke 4 cards ka type -> label, icon aur colors yaha fix hain, sirf count/percent data se aayega
enum class StatType(
    val label: String,
    val icon: ImageVector,
    val tint: Color,
    val bg: Color
) {
    TOTAL("Total Complaints", Icons.Default.Description,
    Color(0xFF2F80ED), Color(0xFFEFF5FF)),

    NEW("New Complaints", Icons.Default.Schedule,
    Color(0xFFF2994A), Color(0xFFFFF7EC)),

    REVIEWED("Reviewed", Icons.Default.Visibility,
    Color(0xFF8B5CF6), Color(0xFFF5F0FF)),

    COMPLETED("Completed", Icons.Default.CheckCircle,
    Color(0xFF27AE60), Color(0xFFEDFAF3)),

    CANCELLED("Cancelled", Icons.Default.Cancel,
    Color(0xFFD32F2F), Color(0xFFFFEEEE))
}

data class OverviewStat(
    val type: StatType,
    val count: Long,

)

// Category -> donut ka color + complaint list ka icon, dono yahi se
enum class ComplaintCategory(val label: String, val color: Color, val icon: ImageVector) {
    ELECTRICAL("Electrical", Color(0xFF2F80ED), Icons.Default.Bolt),
    PLUMBING("Plumbing", Color(0xFFFFA726), Icons.Default.WaterDrop),
    CLEANING("Cleaning", Color(0xFF3DBE6C), Icons.Default.CleaningServices),
    APPLIANCE("Appliance", Color(0xFFB57EDC), Icons.Default.HomeRepairService),
    OTHERS("Others", Color(0xFF9FB0CC), Icons.Default.MoreHoriz)
}

// Donut chart ye list se draw hota hai -> list change hui to chart apne aap change
data class CategorySlice(val category: ComplaintCategory, val count: Long)



data class Complaint(
    val id: String,
    val title: String,
    val location: String,
    val category: ComplaintCategory,
    val timeAgo: String
)

enum class TimeRange(val label: String,val days:Int) {
    LAST_7_DAYS("Last 7 Days",7),
    LAST_30_DAYS("Last 30 Days",30),
    THIS_YEAR("This Year",365)
}

// Poori screen ki state ek hi jagah
data class DashboardUiState(
    val wardenName: String = "",
    val wardenRole: String = "",
    val hostelName: String = "",
    val hasUnreadNotifications: Boolean = false,
    val selectedRange: TimeRange = TimeRange.LAST_7_DAYS,
    val overview: List<OverviewStat> = emptyList(),
    val categories: List<CategorySlice> = emptyList(),
    val recentComplaints: List<Complaint> = emptyList()
)

// ===================== VIEWMODEL =====================


// ===================== SCREEN =====================
@Composable
fun DashboardScreenG(
    navController: NavController,
    name: String,
    viewModel: DashBoardViewModel = hiltViewModel()
) {
    //val state by viewModel.uiState.collectAsState()

    val isLoadingStats = viewModel.isLoadingStats
    val isLoadingCategories = viewModel.isLoadingServiceType
    val isLoadingComplaints = viewModel.isLoadingRecentService

    val errorStats = viewModel.errorMessageStats
    val errorCategories = viewModel.errorMessageServiceType
    val errorComplaints = viewModel.errorMessageRecentService

    var selectedRange by remember {
        mutableStateOf(TimeRange.LAST_7_DAYS)
    }


    LaunchedEffect(viewModel) {
        viewModel.loadCountStats(selectedRange.days)
        viewModel.loadServiceType()
        viewModel.loadRecentService()
    }

    val stats = viewModel.serviceStats;
    val categories = viewModel.serviceType;
    val complaints = viewModel.recentService





    val overviewStats = stats?.let {
        listOf(
            OverviewStat(StatType.TOTAL, it.total?:0L),
            OverviewStat(StatType.NEW, it.newcount?:0L),
            OverviewStat(StatType.REVIEWED, stats.reviewed?:0L),
            OverviewStat(StatType.COMPLETED, it.completed?:0L),
            OverviewStat(StatType.CANCELLED, it.cancelled?:0L)
        )
    } ?: emptyList()

    val categorySlices = categories?.let {
        listOf(
            CategorySlice(ComplaintCategory.ELECTRICAL, it.electrician?:0L),
            CategorySlice(ComplaintCategory.PLUMBING, it.plumbing?:0L),
            CategorySlice(ComplaintCategory.CLEANING, it.cleaning?:0L),
            CategorySlice(ComplaintCategory.APPLIANCE, it.appliance?:0L)
        )
    } ?: emptyList()

    val recentComplaints = complaints.take(5).map { item ->

        val category = when (item.serviceType.lowercase()) {
            "plumbing" -> ComplaintCategory.PLUMBING
            "electrician", "electrical" -> ComplaintCategory.ELECTRICAL
            "cleaning" -> ComplaintCategory.CLEANING
            "appliance" -> ComplaintCategory.APPLIANCE
            else -> ComplaintCategory.APPLIANCE
        }

        Complaint(
            id = item.bookingId,
            title = item.problemDesc?.takeIf { it.isNotBlank() }
                ?: "No description provided",
            location = listOfNotNull(
                item.location,
                item.roomNo
            ).joinToString(" • ").ifBlank {
                "Location unavailable"
            },
            category = category,
            timeAgo = item.timeCalculated
        )
    }


    Scaffold(
        containerColor = ScreenBgG,
        bottomBar = { BottomBarG(navController) }
    ) { padding ->
        // Poori screen scrollable
         Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
                //.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TopBarG(
                hasUnread = false,
                onMenuClick = { /* TODO: drawer open */ },
                onBellClick = { /* TODO: navController.navigate("notifications") */ }
            )
            WelcomeCardG(name, "Employee", "Hostel")
            OverviewHeaderG(selected = selectedRange,
                onSelect = {range ->
                    selectedRange = range
                    viewModel.loadCountStats(range.days)})

             when {
                 isLoadingStats -> {
                     Box(
                         modifier = Modifier
                             .fillMaxWidth()
                             .height(120.dp),
                         contentAlignment = Alignment.Center
                     ) {
                         CircularProgressIndicator()
                     }
                 }

                 errorStats != null -> {
                     Text(
                         text = errorStats,
                         color = Color.Red,
                         modifier = Modifier.padding(horizontal = 16.dp)
                     )

                     TextButton(
                         onClick = { viewModel.loadCountStats(selectedRange.days) }
                     ) {
                         Text("Retry")
                     }
                 }

                 else -> {
                     OverviewRowG(overviewStats)
                 }
             }
            //OverviewRowG(overviewStats)

             when {
                 isLoadingCategories -> {
                     CircularProgressIndicator(
                         modifier = Modifier.padding(16.dp)
                     )
                 }

                 errorCategories != null -> {
                     Text(
                         text = errorCategories,
                         color = Color.Red
                     )

                     TextButton(onClick = { viewModel.loadServiceType() }) {
                         Text("Retry")
                     }
                 }

                 else -> {
                     CategoryCardG(categorySlices)
                 }
             }
            RecentHeaderG(onViewAll = { /* TODO: navController.navigate("complaints") */ })
             when {
                 isLoadingComplaints -> {
                     CircularProgressIndicator(
                         modifier = Modifier.padding(16.dp)
                     )
                 }

                 errorComplaints != null -> {
                     Text(
                         text = errorComplaints,
                         color = Color.Red
                     )

                     TextButton(onClick = { viewModel.loadRecentService() }) {
                         Text("Retry")
                     }
                 }

                 recentComplaints.isEmpty() -> {
                     Text(
                         text = "No recent complaints found",
                         color = SubtleTextG,
                         modifier = Modifier.padding(16.dp)
                     )
                 }

                 else -> {
                     RecentComplaintsCardG(recentComplaints)
                 }
             }

        }
    }
}

// ===================== TOP BAR =====================
@Composable
fun TopBarG(hasUnread: Boolean, onMenuClick: () -> Unit, onBellClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onMenuClick) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = NavyG)
        }
        Spacer(Modifier.width(8.dp))
        Icon(Icons.Default.Home, contentDescription = null, tint = NavyG, modifier = Modifier.size(36.dp))
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text("Home Support", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NavyG)
            Text("Hostel Complaint Management", fontSize = 12.sp, color = SubtleTextG)
        }
        // Bell + red dot (unread ho tabhi)
        Box {
            IconButton(onClick = onBellClick) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = NavyG)
            }
            if (hasUnread) {
                Box(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 10.dp)
                        .size(9.dp)
                        .background(Color.Red, CircleShape)
                )
            }
        }
    }
}

// ===================== WELCOME CARD =====================
@Composable
fun WelcomeCardG(name: String, role: String, hostel: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F1FD), RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("👋", fontSize = 28.sp)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("Good Morning,", fontSize = 14.sp, color = NavyG) // TODO: time ke hisaab se Morning/Afternoon/Evening
            Text("$name", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = NavyG)
            Text("$role  •  $hostel", fontSize = 13.sp, color = SubtleTextG)
        }
        // Profile avatar
        Box(
            Modifier
                .size(56.dp)
                .background(Color.White, CircleShape)
                .border(2.dp, Color(0xFFD6E4FA), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, null, tint = Color(0xFF1E4DB7), modifier = Modifier.size(30.dp))
        }
    }
}

// ===================== OVERVIEW =====================
@Composable
fun OverviewHeaderG(selected: TimeRange, onSelect: (TimeRange) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Overview", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NavyG, modifier = Modifier.weight(1f))
        Box {
            Row(
                modifier = Modifier.clickable { expanded = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(selected.label, fontSize = 14.sp, color = SubtleTextG)
                Icon(Icons.Default.KeyboardArrowDown, null, tint = SubtleTextG)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                TimeRange.values().forEach { range ->
                    DropdownMenuItem(
                        text = { Text(range.label) },
                        onClick = { expanded = false; onSelect(range) }
                    )
                }
            }
        }
    }
}

@Composable
fun OverviewRowG(stats: List<OverviewStat>) {
    // List jitni badi hogi utne cards weight se barabar bat jayenge
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        items(stats) { stat -> StatCardG(stat, modifier = Modifier.width(150.dp)) }
    }
}

@Composable
fun StatCardG(stat: OverviewStat, modifier: Modifier = Modifier) {
    //val isUp = stat.changePercent >= 0
    //val trendColor = if (isUp) Color(0xFF27AE60) else Color(0xFFD32F2F)

    Column(
        modifier = modifier
            .background(stat.type.bg, RoundedCornerShape(16.dp))
            .border(1.dp, stat.type.tint.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
            .padding(10.dp)
    ) {
        Box(
            Modifier.size(38.dp).background(stat.type.tint.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(stat.type.icon, null, tint = stat.type.tint, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.height(10.dp))
        Text(stat.count.toString(), fontSize = 26.sp, fontWeight = FontWeight.Bold, color = NavyG)
        Text(stat.type.label, fontSize = 11.sp, color = SubtleTextG, lineHeight = 14.sp, minLines = 2)
        Spacer(Modifier.height(6.dp))

        //We are not Calculating change Percentage
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(
//                if (isUp) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
//                null, tint = trendColor, modifier = Modifier.size(12.dp)
//            )
//            //Text("${kotlin.math.abs(stat.changePercent)}%", fontSize = 11.sp, color = trendColor, fontWeight = FontWeight.SemiBold)
//        }
//        //Text("vs last week", fontSize = 9.sp, color = SubtleTextG)
    }
}

// ===================== CATEGORY CARD (DONUT) =====================
@Composable
fun CategoryCardG(slices: List<CategorySlice>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, CardBorderG, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text("Complaints by Category", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyG)
        Spacer(Modifier.height(14.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            DonutChartG(slices, Modifier.size(150.dp))
            Spacer(Modifier.width(20.dp))
            CategoryLegendG(slices, Modifier.weight(1f))
        }
    }
}

// Dynamic donut: har slice ka angle = count / total * 360, data change hote hi re-animate hota hai
@Composable
fun DonutChartG(slices: List<CategorySlice>, modifier: Modifier = Modifier) {
    val total = slices.sumOf { it.count }

    // 0 -> 1 animation, slices change hone par dobara chalta hai
    val progress = remember { Animatable(0f) }
    LaunchedEffect(slices) {
        progress.snapTo(0f)
        progress.animateTo(1f, tween(durationMillis = 800))
    }

    Box(modifier, contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val strokeWidth = size.minDimension * 0.2f
            val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
            val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)

            if (total == 0L) {
                // Data nahi hai to grey ring
                drawArc(Color(0xFFE3EAF6), 0f, 360f, false, topLeft, arcSize, style = Stroke(strokeWidth))
                return@Canvas
            }

            val gap = if (slices.size > 1) 2f else 0f // slices ke beech chhota gap
            var startAngle = -90f
            slices.forEach { slice ->
                val sweep = slice.count.toFloat() / total * 360f * progress.value
                drawArc(
                    color = slice.category.color,
                    startAngle = startAngle,
                    sweepAngle = (sweep - gap).coerceAtLeast(0f),
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
                startAngle += sweep
            }
        }
        // Center me total (sum of counts)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(total.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = NavyG)
            Text("Total", fontSize = 12.sp, color = SubtleTextG)
        }
    }
}

@Composable
fun CategoryLegendG(slices: List<CategorySlice>, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        slices.forEach { slice ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(10.dp).background(slice.category.color, CircleShape))
                Spacer(Modifier.width(8.dp))
                Text(slice.category.label, fontSize = 14.sp, color = SubtleTextG, modifier = Modifier.weight(1f))
                Text(slice.count.toString(), fontSize = 14.sp, color = SubtleTextG)
            }
        }
    }
}

// ===================== RECENT COMPLAINTS =====================
@Composable
fun RecentHeaderG(onViewAll: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Recent Complaints", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NavyG, modifier = Modifier.weight(1f))
        Row(Modifier.clickable { onViewAll() }, verticalAlignment = Alignment.CenterVertically) {
            Text("View All", fontSize = 14.sp, color = BlueG)
            Icon(Icons.Default.ChevronRight, null, tint = BlueG)
        }
    }
}

@Composable
fun RecentComplaintsCardG(complaints: List<Complaint>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, CardBorderG), RoundedCornerShape(16.dp))
            .padding(horizontal = 14.dp)
    ) {
        complaints.forEachIndexed { index, complaint ->
            ComplaintItemG(complaint)
            // Last item ke baad divider nahi
            if (index != complaints.lastIndex) HorizontalDivider(color = CardBorderG)
        }
    }
}

@Composable
fun ComplaintItemG(complaint: Complaint) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: navController.navigate("complaint_details/${complaint.id}") */ }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Category icon
        Box(
            Modifier.size(48.dp).background(complaint.category.color.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(complaint.category.icon, null, tint = complaint.category.color)
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(complaint.id, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = NavyG)
            Text(complaint.title, fontSize = 14.sp, color = SubtleTextG)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, null, tint = SubtleTextG, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(2.dp))
                Text(complaint.location, fontSize = 12.sp, color = SubtleTextG)
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            //PriorityChipG(complaint.priority)
            Spacer(Modifier.height(6.dp))
            Text(complaint.timeAgo, fontSize = 12.sp, color = SubtleTextG)
        }
    }
}



// ===================== BOTTOM BAR =====================
private data class NavItemG(val label: String, val icon: ImageVector, val route: String)

@Composable
fun BottomBarG(navController: NavController) {
    val items = listOf(
        NavItemG("Dashboard", Icons.Default.Home, "dashboard"),
        NavItemG("Complaints", Icons.Default.Description, "complaints"),
        NavItemG("Staff", Icons.Default.Groups, "staff"),
        NavItemG("Reports", Icons.Default.BarChart, "reports"),
        NavItemG("Profile", Icons.Default.Person, "profile")
    )
    // TODO: selected ko navController ke current route se derive kar (currentBackStackEntryAsState)
    var selected by remember { mutableIntStateOf(0) }

    NavigationBar(containerColor = Color.White) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selected == index,
                onClick = {
                    selected = index
                    // TODO: navController.navigate(item.route)
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label, fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BlueG,
                    selectedTextColor = BlueG,
                    unselectedIconColor = Color(0xFF8A9BBB),
                    unselectedTextColor = Color(0xFF8A9BBB),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


