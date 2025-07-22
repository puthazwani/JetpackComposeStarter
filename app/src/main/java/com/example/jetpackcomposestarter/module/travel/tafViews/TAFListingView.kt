package com.example.jetpackcomposestarter.module.travel.tafViews

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.jetpackcomposestarter.shared.components.toLocalDateOrNull
import java.time.LocalDate
import java.util.Date

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
    val insertTafFormResponse by viewModel.insertTafFormState.collectAsStateWithLifecycle()
    val tafNavItems = listOf(
        BottomNavItem("My TAF", Icons.Default.CalendarToday),
        BottomNavItem("My Pending TAF", Icons.Default.Description, badgeCount = 3),
        BottomNavItem("All TAF", Icons.Default.Description)
    )
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            RTAFBottomNavigationBar(
                items = tafNavItems,
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        when(val response = tafListingResponse) {
            is Response.Idle -> {}
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> { val tafList = response.data
                if (tafList.isEmpty()) {
                    EmptyListContent(innerPadding = innerPadding)
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),

                    ) {
                        items(
                            items = tafList,
                            key = { it.id }
                        ) { tafItem ->
                            TAFListItem(
                                item = tafItem,
                                onClick = { navigateToTAFFormDetailsScreen(tafItem) },
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
            is Response.Failure -> response.e.message?.let { errorMessage ->
                LaunchedEffect(errorMessage) {
//                    logMessage(errorMessage)
//                    showToastMessage(context, errorMessage)
                }
            }
        }
    }
}

@Composable
fun TAFListItem(
    item: TravelAuthorisationEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statusColor = statusToColor(item.applicationStatus)
    val statusBgColor = statusColor.copy(alpha = 0.08f)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(statusBgColor)
            .clickable { onClick() }
            .padding(start = 0.dp, top = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status Line
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(statusColor)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                val startDate = item.dateStart.toLocalDateOrNull()
                val endDate = item.dateEnd.toLocalDateOrNull()

                // fallback values if null
                val displayStart = startDate ?: LocalDate.now()
                val displayEnd = endDate ?: displayStart

                val isSameDate = startDate == endDate

                DateIconView(date = displayStart)

                Spacer(modifier = Modifier.width(6.dp))

                DateIconView(
                    date = displayEnd,
                    modifier = Modifier.alpha(if (isSameDate) 0f else 1f)
                )
            }


            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "#${item.applicationReferenceNumber}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = item.destination,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Navigate",
                tint = MaterialTheme.colorScheme.outline
            )
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

//@Preview(showBackground = true)
//@Composable
//fun RTAFListingScreenPreview() {
//    JetpackComposeStarterTheme {
//        RTAFListingScreen()
//    }
//}

@Preview(showBackground = true)
@Composable
fun PreviewTAFListingScreen() {
    val sampleList = listOf(
        TravelAuthorisationEntity(
            id = 1,
            destination = "KL Business Trip",
            applicationStatus = "Approved",
            employeeId = 1001,
            employeeNo = "EMP001",
            personId = 2001,
            localOversea = "Local",
            dateStart = Date(),
            dateEnd = Date(),
            inbound = null,
            outbound = null,
            transport = "Car",
            remarks = "Meeting with client",
            dateCreated = Date(),
            createdBy = 0,
            dateModified = Date(),
            modifiedBy = 0,
            dateSubmitted = Date(),
            submittedByPersonId = 2001,
            actionedByPersonId = 2002,
            applicationSource = "Mobile",
            applicationReferenceNumber = "TAF-001",
            currentApprovers = "Manager A",
            dateCurrentApprovalStart = Date(),
            dateWorkflowCompleted = null,
            workflowComment = "Looks good"
        ),
        TravelAuthorisationEntity(
            id = 2,
            destination = "Penang Conference",
            applicationStatus = "Pending",
            employeeId = 1002,
            employeeNo = "EMP002",
            personId = 2002,
            localOversea = "Local",
            dateStart = Date(),
            dateEnd = Date(),
            inbound = null,
            outbound = null,
            transport = "Train",
            remarks = "Tech conference",
            dateCreated = Date(),
            createdBy = 0,
            dateModified = Date(),
            modifiedBy = 0,
            dateSubmitted = null,
            submittedByPersonId = null,
            actionedByPersonId = null,
            applicationSource = "Web",
            applicationReferenceNumber = "TAF-002",
            currentApprovers = "Manager B",
            dateCurrentApprovalStart = null,
            dateWorkflowCompleted = null,
            workflowComment = ""
        )
    )

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(sampleList) { taf ->
                TAFListItem(item = taf, onClick = {})
            }
        }
    }
}

