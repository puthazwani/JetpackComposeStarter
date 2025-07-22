package com.example.jetpackcomposestarter.module.travel.tafViews

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpackcomposestarter.shared.components.Dropdown
import com.example.jetpackcomposestarter.shared.components.FabIcon
import com.example.jetpackcomposestarter.shared.components.FabOption
import com.example.jetpackcomposestarter.shared.components.MultiFabItem
import com.example.jetpackcomposestarter.shared.components.MultiFloatingActionButton
import com.example.jetpackcomposestarter.shared.components.MultiLineFormTextField
import com.example.jetpackcomposestarter.shared.theme.JetpackComposeStarterTheme
import com.example.jetpackcomposestarter.R
import com.example.jetpackcomposestarter.Response
import com.example.jetpackcomposestarter.ResponseHandler
import com.example.jetpackcomposestarter.data.local.entities.TravelAuthorisationEntity
import com.example.jetpackcomposestarter.module.travel.tafmanagers.InsertTafFormResponse
import com.example.jetpackcomposestarter.module.travel.tafmanagers.TAFListingViewModel
import com.example.jetpackcomposestarter.shared.components.LoadingIndicator
import com.example.jetpackcomposestarter.shared.components.SingleDatePickerField
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

const val EMPTY_STRING = ""

@Composable
fun TAFFormScreen(
    onInsertTAFForm: (taf: TravelAuthorisationEntity) -> Unit,
    insertTafFormState: StateFlow<InsertTafFormResponse>,
    onClearInsertState: () -> Unit,
//    onDeleteTAFForm: (taf: TravelAuthorisationEntity) -> Unit,
//    deleteTafFormState: StateFlow<Response<Boolean>>,
//    onClearDeleteState: () -> Unit
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val insertState by insertTafFormState.collectAsStateWithLifecycle()
//    val deleteState by deleteTafFormState.collectAsStateWithLifecycle()

    // Insert State Handler
    LaunchedEffect(insertState) {
        when (insertState) {
            is Response.Success -> {
                snackbarHostState.showSnackbar("TAF saved successfully!")
                onClearInsertState()
            }
            is Response.Failure -> {
                val error = (insertState as Response.Failure).e.localizedMessage ?: "Unknown error"
                snackbarHostState.showSnackbar("Failed to save TAF: $error")
                onClearInsertState()
            }
            else -> Unit
        }
    }

    // Delete State Handler
//    LaunchedEffect(deleteState) {
//        when (deleteState) {
//            is Response.Success -> {
//                snackbarHostState.showSnackbar("TAF deleted successfully!")
//                onClearDeleteState()
//            }
//            is Response.Failure -> {
//                val error = (deleteState as Response.Failure).e.localizedMessage ?: "Unknown error"
//                snackbarHostState.showSnackbar("Failed to delete TAF: $error")
//                onClearDeleteState()
//            }
//            else -> Unit
//        }
//    }

    var travelType by remember { mutableStateOf("") }
    var transportType by remember { mutableStateOf("") }
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var selectedDate by remember { mutableStateOf(LocalDate.now().format(dateFormatter)) }
    var destination by remember { mutableStateOf(EMPTY_STRING) }
    var remarks by remember { mutableStateOf(EMPTY_STRING) }
    val localOverseaOptions = listOf("None Specified", "Local", "Overseas")
    val transportOptions = listOf(
        "None Specified",
        "No Transport Required",
        "Air - First",
        "Air - Business",
        "Air - Economy",
        "Company's Vehicle",
        "Own Vehicle",
        "Commercial"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            floatingActionButton = {
                MultiFloatingActionButton(
                    fabIcon = FabIcon(
                        iconRes = R.drawable.ic_baseline_more_horiz_24,
                        iconResAfterRotate = R.drawable.ic_baseline_more_horiz_24,
                        iconRotate = 180f
                    ),
                    fabOption = FabOption(
                        iconTint = Color.Blue,
                        showLabels = true,
                        backgroundTint = Color.White,
                    ),
                    itemsMultiFab = listOf(
                        MultiFabItem(
                            icon = R.drawable.ic_baseline_save_24,
                            label = "Save",
                        ),
                        MultiFabItem(
                            icon = R.drawable.ic_baseline_delete_24,
                            label = "Delete",
                        ),
                    ),
                    onFabItemClicked = { item ->
                        when (item.label) {
                            "Save" -> {
                                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                val parsedDate: Date = formatter.parse(selectedDate) ?: Date()
                                val taf = TravelAuthorisationEntity(
                                    destination = destination.takeIf { it.isNotBlank() }.toString(),
                                    applicationStatus = "Draft",
                                    employeeId = -1,
                                    employeeNo = "",
                                    personId = -1,
                                    localOversea = travelType.takeIf { it.isNotBlank() && it != "None Specified" },
                                    dateStart = parsedDate,
                                    dateEnd = parsedDate,
                                    inbound = null,
                                    outbound = null,
                                    transport = transportType.takeIf { it.isNotBlank() && it != "None Specified" },
                                    remarks = remarks.takeIf { it.isNotBlank() },
                                    dateCreated = Date(),
                                    createdBy = -1,
                                    dateModified = Date(),
                                    modifiedBy = -1,
                                    dateSubmitted = Date(),
                                    submittedByPersonId = null,
                                    actionedByPersonId = null,
                                    applicationSource = "Mobile-Android",
                                    applicationReferenceNumber = "",
                                    currentApprovers = "",
                                    dateCurrentApprovalStart = null,
                                    dateWorkflowCompleted = null,
                                    workflowComment = ""
                                )
                                onInsertTAFForm(taf)
                            }

//                            "Delete" -> {
//                                savedTafForm?.let {
//                                    onDeleteTAFForm(it)
//                                }
//                            }
                        }
                    },
                    fabTitle = "MultiFloatActionButton",
                    showFabTitle = false
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Travel Type", style = MaterialTheme.typography.titleMedium)
                        Dropdown(
                            label = "Please Select",
                            items = localOverseaOptions,
                            selectedItem = travelType,
                            onItemSelected = { travelType = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Date", style = MaterialTheme.typography.titleMedium)
                        SingleDatePickerField(
                            label = "",
                            selectedDate = selectedDate,
                            onDateSelected = { selectedDate = it }
                        )


                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Destination", style = MaterialTheme.typography.titleMedium)
                        MultiLineFormTextField(
                            destination,
                            { destination = it },
                            "",
                            maxLength = 50
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Transport", style = MaterialTheme.typography.titleMedium)
                        Dropdown(
                            label = "Please Select",
                            items = transportOptions,
                            selectedItem = transportType,
                            onItemSelected = { transportType = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Remarks", style = MaterialTheme.typography.titleMedium)
                        MultiLineFormTextField(remarks, { remarks = it }, "")
                    }
                }
            }
        }
    }
}

@Composable
fun TAFFormScreenHost(
    navController: NavController,
    viewModel: TAFListingViewModel = hiltViewModel()
) {
    TAFFormScreen(
        onInsertTAFForm = { viewModel.insertTAFForm(it) },
        insertTafFormState = viewModel.insertTafFormState,
        onClearInsertState = {
            viewModel.clearInsertState()
            navController.popBackStack()
        },
//        onDeleteTAFForm = { viewModel.deleteTAFForm(it) },
//        deleteTafFormState = viewModel.deleteTafFormState,
//        onClearDeleteState = {
//            viewModel.clearDeleteState()
//            navController.popBackStack()
//        }
    )
}

