package com.example.jetpackcomposestarter.module.travel

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposestarter.shared.components.DateIconView
import java.time.LocalDate


@Composable
fun RTAFListingScreen(
    canNavigateBack: Boolean = false,
//    navigateToRTAFFormScreen: (Book) -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
//        topBar = {
//            SimpleTopAppBar()
//        },
//        snackbarHost = {
//            SnackbarHost(
//                hostState = snackbarHostState
//            )
//        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "2023",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
            )

            RTAFRowItem(
                item = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                startDate = LocalDate.of(2023, 6, 1),
                endDate = LocalDate.of(2023, 6, 3),
                refNo = "00899928398",
                destination = "Masjid Damansara Perdana",
                requestedAmount = 0.00,
                approvedAmount = 0.00,
                applicationStatus = "Rejected",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun RTAFRowItem(
    item: List<String>,
    modifier: Modifier = Modifier,
    startDate: LocalDate,
    endDate: LocalDate,
    refNo: String,
    destination: String,
    applicationStatus: String,
    requestedAmount: Double,
    approvedAmount: Double,
    statusColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {},
) {
    Column(
//        modifier = modifier.background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            item.forEach { item ->
                val lineStatusColor = statusToColor(applicationStatus)
                val bgStatusColor = lineStatusColor.copy(alpha = 0.08f)
                Column(
                    modifier = Modifier
                        .background(bgStatusColor)
                        .fillMaxWidth()
                        .clickable { onClick() }
                        .padding(start = 0.dp, top = 12.dp, end = 12.dp, bottom = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(6.dp)
                                .fillMaxHeight()
                                .background(lineStatusColor)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            DateIconView(date = startDate)
                            Spacer(modifier = Modifier.width(6.dp))
                            DateIconView(date = endDate)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "#$refNo",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = destination,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Requested: MYR %.2f".format(requestedAmount),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "Approved: MYR %.2f".format(approvedAmount),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = "Arrow",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

fun statusToColor(applicationStatus: String): Color {
    return when (applicationStatus.lowercase()) {
        "approved" -> Color(0xFF4CAF50)
        "pending" -> Color(0xFFFFC107)
        "rejected" -> Color(0xFFF44336)
        else -> Color.LightGray
    }
}

@Preview(showBackground = true)
@Composable
fun RTAFListingScreenPreview() {
        RTAFListingScreen()
}
