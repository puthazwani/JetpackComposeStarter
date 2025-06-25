package com.example.jetpackcomposestarter.module.travel.tafViews

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposestarter.shared.components.Dropdown
import com.example.jetpackcomposestarter.shared.components.FormTextField
import com.example.jetpackcomposestarter.shared.theme.JetpackComposeStarterTheme

@Composable
fun TAFFormScreen() {
    var selected by remember { mutableStateOf("") }
    val localOverseaOptions = listOf("Local", "Overseas")
    val transportOptions = listOf(
        "Please select",
        "No Transport Required",
        "Air - First",
        "Air - Business",
        "Air - Economy",
        "Company's Vehicle",
        "Own Vehicle",
        "Commercial"
    )
    var destination by remember { mutableStateOf("") }
    var remarks by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
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
                    .fillMaxWidth()
            ) {
                Text("Travel Type", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Dropdown(
                    label = "Please Select",
                    items = localOverseaOptions,
                    selectedItem = selected,
                    onItemSelected = { selected = it }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("Destination", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                FormTextField(destination, { destination = it }, "")

                Spacer(modifier = Modifier.height(12.dp))
                Text("Transport", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Dropdown(
                    label = "Please Select",
                    items = transportOptions,
                    selectedItem = selected,
                    onItemSelected = { selected = it }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("Remarks", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                FormTextField(remarks, { remarks = it }, "")

            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun TAFFormScreenPreviewLight() {
    JetpackComposeStarterTheme(darkTheme = false) {
        TAFFormScreen()
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TAFFormScreenPreviewDark() {
    JetpackComposeStarterTheme(darkTheme = true) {
        TAFFormScreen()
    }
}
