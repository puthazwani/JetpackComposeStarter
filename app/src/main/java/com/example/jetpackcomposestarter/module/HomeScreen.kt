package com.example.jetpackcomposestarter.module

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposestarter.R
import com.example.jetpackcomposestarter.shared.theme.*

data class GridItem(
    val imageRes: Int,
    val backgroundColor: Color,
    val label: String
)

@Composable
fun EmptyState() {
    Column(
        Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text = stringResource(id = R.string.empty_screen_title),
//            style = MaterialTheme.typography.titleLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.primary,
//        )
//        Text(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            text = stringResource(id = R.string.empty_screen_subtitle),
//            style = MaterialTheme.typography.bodySmall,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.outline,
//        )

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

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EmptyState()
}

