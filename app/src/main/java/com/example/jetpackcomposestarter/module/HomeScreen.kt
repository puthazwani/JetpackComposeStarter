package com.example.jetpackcomposestarter.module

import android.widget.CalendarView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposestarter.R
import com.example.jetpackcomposestarter.shared.theme.*
import java.time.LocalDate
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.jetpackcomposestarter.shared.components.MonthCalendarView

data class GridItem(
    val imageRes: Int,
    val backgroundColor: Color,
    val label: String
)

enum class EventType {
    Company, Personal, Public
}

@Composable
fun HomeScreen() {
    val itemsList = listOf(
        GridItem(R.drawable.pendingtask, ModulePendingItems, "Pend. Tasks"),
        GridItem(R.drawable.calendar, ModuleLeave, "Leave"),
        GridItem(R.drawable.claims, ModuleClaims, "Claims"),
        GridItem(R.drawable.calendar, ModuleSchedule, "Schedule"),
        GridItem(R.drawable.payslip, ModulePayslip, "Payslip"),
        GridItem(R.drawable.travel, ModuleTravel, "Travel"),
        GridItem(R.drawable.payslip, ModulePayslip, "Overtime"),
        GridItem(R.drawable.claims, ModuleClaims, "Bulk Overtime"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // User Info
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = painterResource(id = R.drawable.user),
//                contentDescription = "Profile",
//                modifier = Modifier
//                    .size(50.dp)
//                    .clip(CircleShape)
//            )
//            Spacer(modifier = Modifier.width(12.dp))
//            Column {
//                Text("Hairol Ariffein Bin Sahari", style = MaterialTheme.typography.titleMedium)
//                Text("Software Engineer, IT Dept", style = MaterialTheme.typography.bodySmall)
//            }
//        }

//        Spacer(modifier = Modifier.height(24.dp))

        // Attendance Overview
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Attendance Overview", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Clock In", style = MaterialTheme.typography.bodyMedium)
                            Text("--:--", style = MaterialTheme.typography.bodyMedium)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Clock Out", style = MaterialTheme.typography.bodyMedium)
                            Text("--:--", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listOf(
                        Pair("QR Scan", R.drawable.qrscan),
                        Pair("Check-In", R.drawable.checkin)
                    ).forEach { (label, icon) ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .clickable { },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = icon),
                                    contentDescription = label,
                                    modifier = Modifier.size(24.dp),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    label,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Annual Leave Balance
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Left: Icon + Texts
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Circular icon background
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = "Calendar",
                                    modifier = Modifier.size(24.dp)
                                )

                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "12.0 Days",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "/ 18.0 Days",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Text(
                                    "Annual Leave Balance",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Go",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Module Overview
        Text("Module Overview", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onTertiaryContainer)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            // Chunk items in groups for vertical stacking
            items(itemsList.chunked(2)) { pair ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    pair.forEach { item ->
                        Card(
                            modifier = Modifier
                                .width(180.dp)
                                .height(50.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 12.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = item.imageRes),
                                        contentDescription = item.label,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = item.label,
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }

                                if (item.label == "Pend. Tasks") {
                                    Box(
                                        modifier = Modifier
                                            .background(Color.Red, CircleShape)
                                            .size(24.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("25", color = Color.White, style = MaterialTheme.typography.labelSmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Office Announcement
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Office Announcement", style = MaterialTheme.typography.titleMedium)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { /* Handle view all click */ }
                    ) {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "View All",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                AnnouncementCard(
                    avatarRes = R.drawable.avatar3,
                    name = "Amanda Yip",
                    position = "HR Officer",
                    title = "Annual Leave and Medical Leave Policy",
                    description = "Please refer to the latest leave guidelines in the attached document.",
                    fileName = "FFHR_2025_007_Remote_Work_Guidelines.pdf"
                )
                AnnouncementCard(
                    avatarRes = R.drawable.avatar1,
                    name = "Raymond Tan",
                    position = "HR Manager",
                    title = "Leave Carry Forward Notice",
                    description = "Reminder: Max 5 days can be carried forward to next year",
                    fileName = "FFHR_2025_014_Leave_Carry_Forward.pdf"
                )
                AnnouncementCard(
                    avatarRes = R.drawable.avatar2,
                    name = "Jonathan Lim",
                    position = "Head of Operations",
                    title = "IMPORTANT REMINDER: Team Leave Planning and Work Schedule for Q3 2025",
                    description = "To ensure smooth project execution during the upcoming quarter, I kindly ask all team members to submit their planned annual leave dates by June 28",
                    fileName = "OPS_2025_003_Q3_Leave_Planning_Guide.pdf"
                )


            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Upcoming Events
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Upcoming Events", style = MaterialTheme.typography.titleMedium)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { /* Handle view all click */ }
                    ) {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "View All",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                EventCard(
                    title = "Townhall Meeting",
                    date = "Thursday, 26 June 2025",
                    time = "01:30 AM – 02:00 AM",
                    eventType = EventType.Company,
                    people = listOf(
                        R.drawable.avatar4,
                        R.drawable.avatar3,
                        R.drawable.avatar2,
                        R.drawable.avatar5,
                        R.drawable.avatar1
                    )
                )
                EventCard("Happy Birthday! SITI YUHANIS AHMAD JAIS", "Thursday, 26 June 2025", eventType = EventType.Personal)
                EventCard("Islamic New Year (Awal Muharam)", "Friday, 27 June 2025", eventType = EventType.Public)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AnnouncementCard(
    avatarRes: Int,
    name: String,
    position: String = "HR Officer",
    title: String,
    description: String,
    fileName: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Avatar + Name + Position
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "$name Avatar",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(name, style = MaterialTheme.typography.labelMedium)
                    Text(position, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Description
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            // PDF icon and filename
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.pdffile),
                    contentDescription = "PDF File",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    fileName,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}



@Composable
fun EventCard(
    title: String,
    date: String,
    time: String? = null,
    eventType: EventType,
    people: List<Int>? = null
) {
    val backgroundColor = when (eventType) {
        EventType.Company -> Color(0xFF0080C5)
        EventType.Personal -> Color(0xFF8572FF)
        EventType.Public -> Color(0xFFDE496E)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Date & Title
            Text(date, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.White, maxLines = 2)

            if (!people.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar list
                    Row(horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
                        val displayAvatars = people.take(4)
                        val remainingCount = people.size - displayAvatars.size

                        displayAvatars.forEach { avatarRes ->
                            Image(
                                painter = painterResource(id = avatarRes),
                                contentDescription = "Attendee",
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                            )
                        }
                        if (remainingCount > 0) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+$remainingCount",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    // Time
                    time?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccessTimeFilled,
                                contentDescription = "Time",
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            } else if (time != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}


@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun HomeScreenPreviewLight() {
    JetpackComposeStarterTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreviewDark() {
    JetpackComposeStarterTheme(darkTheme = true) {
        HomeScreen()
    }
}


