package com.example.reservations.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.reservations.R
import com.example.reservations.viewmodels.ProviderScheduleViewModel
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderScheduleScreen(navController: NavController, viewModel: ProviderScheduleViewModel = hiltViewModel()) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val selectedDateInMillis by viewModel.selectedDateInMillis.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.provider_toolbar_title)) })
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // TODO: Make provider schedule API call
                        navController.navigate("login") {
                            popUpTo("login") {
                                inclusive = true
                            } // Clear the back stack up to login
                        }
                    }
                ) {
                    Text(stringResource(R.string.provider_submit_availability_button_text))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showDatePicker = true }) {
                Text(stringResource(R.string.provider_choose_date_button_text))
            }

            selectedDateInMillis?.let { selectedDateMillis ->
                val localDate = Instant.ofEpochMilli(selectedDateMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                Text(
                    text = stringResource(R.string.provider_selected_date_text, localDate),
                    modifier = Modifier.padding(16.dp),
                )

                // TODO: Out of time
                // Show a view to allow the provider to select a time frame to work
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.onDateSelected(datePickerState.selectedDateMillis)
                            showDatePicker = false
                        }) {
                            Text(stringResource(R.string.provider_date_picker_dialog_ok))
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDatePicker = false }) {
                            Text(stringResource(R.string.provider_date_picker_dialog_cancel))
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}
