package com.example.jetpackcomposestarter.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
