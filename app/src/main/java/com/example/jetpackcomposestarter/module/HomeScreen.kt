package com.example.jetpackcomposestarter.module

import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
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
import java.time.format.DateTimeFormatter
import java.util.Calendar
import androidx.compose.runtime.*
import java.time.DayOfWeek

data class GridItem(
    val imageRes: Int,
    val backgroundColor: Color,
    val label: String
)

@Composable
fun HomeScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        val itemsList = listOf(
            GridItem(R.drawable.pendingtask, ModulePendingItems, "Pend. Tasks"),
            GridItem(R.drawable.qrscan, ModuleCheckIn, "QR Scan"),
            GridItem(R.drawable.checkin, ModuleCheckIn, "Check-In"),
            GridItem(R.drawable.calendar, ModuleLeave, "Leave"),
            GridItem(R.drawable.travel, ModuleTravel, "Travel"),
            GridItem(R.drawable.claims, ModuleClaims, "Claims"),
            GridItem(R.drawable.payslip, ModulePayslip, "Payslip"),
            GridItem(R.drawable.calendar, ModuleSchedule, "Schedule")
        )

        LazyHorizontalGrid(
            modifier = Modifier.height(120.dp),
            rows = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(itemsList) { item ->
                Column(
                    modifier = Modifier
                        .background(item.backgroundColor)
                        .width(150.dp)
                        .wrapContentHeight()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.label,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = item.label, color = Color.White)

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomCalendar(
            selectedDate = selectedDate,
            onDateSelected = { date ->
                selectedDate = date
            }
        )
    }
}

@Composable
fun CustomCalendar(
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit
) {
    var currentMonth by remember { mutableStateOf(selectedDate.withDayOfMonth(1)) }
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.dayOfWeek.value % 7

    val daysOfWeek = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    Column(modifier = Modifier.padding(8.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                currentMonth = currentMonth.minusMonths(1)
            }) {
                Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month", tint = MaterialTheme.colorScheme.primary)
            }

            Text(
                color = MaterialTheme.colorScheme.onSecondary,
                text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                style = MaterialTheme.typography.bodyLarge
            )

            IconButton(onClick = {
                currentMonth = currentMonth.plusMonths(1)
            }) {
                Icon(
                    imageVector = Icons.Default.ChevronRight, contentDescription = "Next Month", tint = MaterialTheme.colorScheme.primary)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            userScrollEnabled = false
        ) {
            // Day headers
            items(daysOfWeek) { day ->
                val isWeekend = day == "SUN" || day == "SAT"
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = if (isWeekend) Color.Red else MaterialTheme.colorScheme.onSecondary
                )
            }
            // Empty slots before the 1st
            items(firstDayOfWeek) {
                Box(modifier = Modifier.aspectRatio(1f))
            }
            // Actual days
            items(daysInMonth) { index ->
                val day = index + 1
                val thisDate = currentMonth.withDayOfMonth(day)
                val isSelected = thisDate == selectedDate
                val isToday = thisDate == LocalDate.now()
                val dayOfWeek = thisDate.dayOfWeek
                val isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(100.dp))
                        .then(
                            if (isSelected) Modifier.background(MaterialTheme.colorScheme.primary)
                            else Modifier
                        )
                        .clickable { onDateSelected(thisDate) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$day",
                        style = MaterialTheme.typography.bodyMedium,
                        color = when {
                            isSelected -> Color.White
                            isToday -> MaterialTheme.colorScheme.primary
                            isWeekend -> Color.Red
                            else -> MaterialTheme.colorScheme.onSecondary
                        }
                    )
                }
            }
        }
    }
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


