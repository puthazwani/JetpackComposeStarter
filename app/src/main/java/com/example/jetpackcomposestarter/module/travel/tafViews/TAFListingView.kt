package com.example.jetpackcomposestarter.module.travel.tafViews

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcomposestarter.Response
import com.example.jetpackcomposestarter.data.local.entities.TravelAuthorisationEntity
import com.example.jetpackcomposestarter.module.travel.tafmanagers.TAFListingViewModel
import com.example.jetpackcomposestarter.shared.components.BottomNavItem
import com.example.jetpackcomposestarter.shared.components.DateIconView
import com.example.jetpackcomposestarter.shared.components.EmptyListContent
import com.example.jetpackcomposestarter.shared.components.LoadingIndicator
import com.example.jetpackcomposestarter.shared.components.RTAFBottomNavigationBar
import java.time.LocalDate


@Composable
fun TAFListingScreen(
    viewModel: TAFListingViewModel = hiltViewModel(),
    navigateToTAFFormDetailsScreen: (TravelAuthorisationEntity) -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val openInsertTafForm by remember { mutableStateOf(false) }
    val tafListingResponse by viewModel.tafListingState.collectAsStateWithLifecycle()
    val insertBookResponse by viewModel.insertTafFormState.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        when(val tafListingResponse = tafListingResponse) {
            is Response.Idle -> {}
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> tafListingResponse.data.let {
                if(it.isEmpty()){
                    EmptyListContent(
                        innerPadding = innerPadding
                    )
                }
            }
            is Response.Failure -> tafListingResponse.e.message?.let { errorMessage ->
                LaunchedEffect(errorMessage) {
//                    logMessage(errorMessage)
//                    showToastMessage(context, errorMessage)
                }
            }
        }

    }
}

@Composable
fun RTAFListingScreen(
    canNavigateBack: Boolean = false,
) {
    val context = LocalContext.current
    val resources = context.resources
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val rtafNavItems = listOf(
        BottomNavItem("My TAF", Icons.Default.CalendarToday),
        BottomNavItem("My Pending TAF", Icons.Default.Description, badgeCount = 3),
        BottomNavItem("All TAF", Icons.Default.Description)
    )
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            RTAFBottomNavigationBar(
                items = rtafNavItems,
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "2023",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
            )

            Text(
                text = "2022",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
            )

            RTAFRowItem(
                item = listOf("Option 1", "Option 2", "Option 3"),
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

//@Composable
//fun TravelAuthorizationRowList(
//    items: List<TravelAuthorization>,
//    onItemClick: (TravelAuthorization) -> Unit
//) {
//    Column {
//        items.forEach { item ->
//            val statusColor = statusToColor(item.applicationStatus)
//            val bgStatusColor = statusColor.copy(alpha = 0.08f)
//
//            Column(
//                modifier = Modifier
//                    .background(bgStatusColor)
//                    .fillMaxWidth()
//                    .clickable { onItemClick(item) }
//                    .padding(horizontal = 12.dp, vertical = 12.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(IntrinsicSize.Min),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .width(6.dp)
//                            .fillMaxHeight()
//                            .background(statusColor)
//                    )
//
//                    Spacer(modifier = Modifier.width(8.dp))
//
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        DateIconView(date = item.dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                        Spacer(modifier = Modifier.width(6.dp))
//                        DateIconView(date = item.dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                    }
//
//                    Spacer(modifier = Modifier.width(8.dp))
//
//                    Column(
//                        modifier = Modifier.weight(1f),
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "#${item.applicationReferenceNumber}",
//                            style = MaterialTheme.typography.labelSmall
//                        )
//                        Text(
//                            text = item.destination,
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            maxLines = 2,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        Text(
//                            text = "Requested: MYR %.2f".format(item.advanceAmount ?: 0.0),
//                            style = MaterialTheme.typography.labelSmall
//                        )
//                        Text(
//                            text = "Status: ${item.applicationStatus}",
//                            style = MaterialTheme.typography.labelSmall
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.width(6.dp))
//
//                    Icon(
//                        imageVector = Icons.Default.ArrowForwardIos,
//                        contentDescription = "Arrow",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                }
//            }
//        }
//    }
//}


fun statusToColor(applicationStatus: String): Color {
    return when (applicationStatus.lowercase()) {
        "approved" -> Color(0xFF4CAF50)
        "pending" -> Color(0xFFFFC107)
        "rejected" -> Color(0xFFF44336)
        else -> Color.LightGray
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RTAFListingScreenPreview() {
//    JetpackComposeStarterTheme {
//        RTAFListingScreen()
//    }
//}
