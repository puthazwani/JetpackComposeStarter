package com.example.jetpackcomposestarter.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val DateIconSize = 50.dp

@Composable
fun DateIconView(date: LocalDate) {
    val monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.getDefault())
    val dayFormatter = DateTimeFormatter.ofPattern("d", Locale.getDefault())

    Box(
        modifier = Modifier
            .size(DateIconSize)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(top = 6.dp)
        ) {
            Text(
                text = date.format(monthFormatter).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.Red,
            )
            Text(
                text = date.format(dayFormatter),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
            )
        }
    }
}

@Composable
fun MonthCalendarView(
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
